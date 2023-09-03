package ua.shtest.casestudy.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

data class ItemsResponse(
    @SerializedName("Devices")
    val devices: List<ItemApiModel>
)