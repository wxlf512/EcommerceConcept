package dev.wxlf.feature.explorer.domain.usecases

import dev.wxlf.feature.explorer.presentation.adapters.abstractions.DisplayableItem
import dev.wxlf.feature.explorer.presentation.adapters.items.*
import io.reactivex.rxjava3.core.Single

class FetchItemsDataUseCase {

    fun execute(): Single<List<DisplayableItem>> =
        Single.just(
            listOf(
                HotSalesListItem(
                    listOf(
                        HotSalesItem("Iphone 12", "Iphone. Lorem. Ipsum.", true),
                        HotSalesItem("Galaxy S20", "Galaxy. Lorem. Ipsum.", false),
                        HotSalesItem("Huawei Mate 10", "Huawei. Lorem. Ipsum.", false)
                    )
                ),
                TitleListItem("Best seller"),
                BestSellersListItem(
                    listOf(
                        BestSellerItem("Samsung Galaxy s20 Ultra", "$1,500", "$1,047"),
                        BestSellerItem("Xiaomi Mi 10 Pro", "$300", "$400"),
                        BestSellerItem("Samsung Galaxy s20 Ultra", "$1,500", "$1,047"),
                        BestSellerItem("Motorola One Edge ", "$300", "$400")
                    )
                )
            )
        )
}