package ua.shtest.casestudy.domain.model.mapper

import ua.shtest.casestudy.data.local.entity.ItemEntity
import ua.shtest.casestudy.domain.model.Item
import ua.shtest.casestudy.utils.DateTimeConverter.formatAsServerDate
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @project Case Study
 * @author Stanislav Humeniuk on 03.09.2023
 * @email stanislav.humeniuk@gmail.com
 */
@Singleton
class ItemsMapper @Inject constructor() {

    fun map(
        item: Item,
    ): ItemEntity {
        return ItemEntity(
            accountId = item.accountId.value,
            serialNumber = item.serialNumber.value,
            deviceType = item.deviceType.value,
            deviceSubType = item.deviceSubType.value,
            name = item.name,
            macAddress = item.macAddress,
            internalIp = item.internalIp,
            firmware = item.firmware,
            platform = item.model,
            lastAliveReported = item.lastAliveReported?.formatAsServerDate(),
            serverAccount = item.serverInfo.account,
            serverDevice = item.serverInfo.device,
            serverEvent = item.serverInfo.event
        )
    }

    fun map(items: List<Item>): List<ItemEntity> = items.map(::map)
}