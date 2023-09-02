package ua.shtest.casestudy.data.remote.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ua.shtest.casestudy.data.remote.model.ItemsResponse

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

interface ItemsApi {

    companion object {
        const val ITEMS_ENDPOINT = "test_android/items.test"
    }

    @GET(ITEMS_ENDPOINT)
    fun fetchItems(): Single<ItemsResponse>
}