package ua.shtest.casestudy.data.local.mapper

import ua.shtest.casestudy.data.local.entity.ItemEntity
import ua.shtest.casestudy.domain.model.AccountId
import ua.shtest.casestudy.domain.model.DeviceSubType
import ua.shtest.casestudy.domain.model.DeviceType
import ua.shtest.casestudy.domain.model.Item
import ua.shtest.casestudy.domain.model.ItemServerInfo
import ua.shtest.casestudy.domain.model.Platform
import ua.shtest.casestudy.domain.model.SerialNumber
import ua.shtest.casestudy.utils.DateTimeConverter.convertServerDateToLocal
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */
@Singleton
class ItemEntityMapper @Inject constructor() {

    fun map(
        itemEntity: ItemEntity,
    ): Item {
        return Item(
            accountId = AccountId(itemEntity.accountId),
            serialNumber = SerialNumber(itemEntity.serialNumber),
            deviceType = DeviceType(itemEntity.deviceType),
            deviceSubType = DeviceSubType(itemEntity.deviceSubType),
            name = itemEntity.name,
            macAddress = itemEntity.macAddress,
            internalIp = itemEntity.internalIp,
            firmware = itemEntity.firmware,
            platform = Platform.createFrom(itemEntity.platform),
            model = itemEntity.platform,
            lastAliveReported = itemEntity.lastAliveReported?.convertServerDateToLocal(),
            serverInfo = ItemServerInfo(
                device = itemEntity.serverDevice,
                account = itemEntity.serverAccount,
                event = itemEntity.serverEvent
            )
        )
    }

    fun map(itemsApiModels: List<ItemEntity>): List<Item> = itemsApiModels.map(::map)
}