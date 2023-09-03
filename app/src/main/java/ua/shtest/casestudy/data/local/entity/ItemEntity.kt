package ua.shtest.casestudy.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

@Entity("items")
data class ItemEntity(
    @PrimaryKey val serialNumber: Long,
    @ColumnInfo(name = "account_id")
    val accountId: Long,
    @ColumnInfo(name = "device_type")
    val deviceType: Int,
    @ColumnInfo(name = "device_sub_type")
    val deviceSubType: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "mac_address")
    val macAddress: String,
    @ColumnInfo(name = "internal_ip")
    val internalIp: String,
    @ColumnInfo(name = "firmware")
    val firmware: String,
    @ColumnInfo(name = "platform")
    val platform: String,
    @ColumnInfo(name = "last_alive_reported")
    val lastAliveReported: String?,
    @ColumnInfo(name = "server_device")
    val serverDevice: String,
    @ColumnInfo(name = "server_account")
    val serverAccount: String,
    @ColumnInfo(name = "server_event")
    val serverEvent: String
)