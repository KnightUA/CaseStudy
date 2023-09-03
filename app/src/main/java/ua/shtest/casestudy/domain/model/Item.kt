package ua.shtest.casestudy.domain.model

import java.io.Serializable
import java.util.Date

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

data class Item(
    val accountId: AccountId,
    val serialNumber: SerialNumber,
    val deviceType: DeviceType,
    val deviceSubType: DeviceSubType,
    val name: String,
    val macAddress: String,
    val internalIp: String,
    val firmware: String,
    val platform: Platform,
    val lastAliveReported: Date?,
    val serverInfo: ItemServerInfo
) : Serializable