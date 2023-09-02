package ua.shtest.casestudy.data.remote.service

import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import ua.shtest.casestudy.data.remote.api.ItemsApi
import ua.shtest.casestudy.data.remote.model.ItemsResponse
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

@Singleton
class ItemsService @Inject constructor(retrofit: Retrofit) : ItemsApi {

    private val itemsApi by lazy { retrofit.create(ItemsApi::class.java) }

    override fun fetchItems(): Observable<ItemsResponse> = itemsApi.fetchItems()
}