package dev.wxlf.feature.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.wxlf.feature.details.domain.mappers.mapToDisplayable
import dev.wxlf.feature.details.domain.usecases.FetchDetailsUseCase
import dev.wxlf.feature.details.presentation.models.DetailsEvent
import dev.wxlf.feature.details.presentation.models.DetailsViewState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val fetchDetailsUseCase: FetchDetailsUseCase) : ViewModel() {

    private val _viewState: MutableLiveData<DetailsViewState> = MutableLiveData()
    val viewState: LiveData<DetailsViewState> = _viewState

    private val compositeDisposable = CompositeDisposable()

    fun obtainEvent(event: DetailsEvent) {
        when(event) {
           DetailsEvent.ScreenShown -> {
               _viewState.postValue(DetailsViewState.LoadingState)
               loadDetails()
           }
        }
    }

    private fun loadDetails() {
        val disposable = fetchDetailsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _viewState.postValue(DetailsViewState.LoadedState(it.mapToDisplayable()))
            }, {
                _viewState.postValue(DetailsViewState.ErrorState(it.localizedMessage.orEmpty()))
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}