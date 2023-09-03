package ua.shtest.casestudy.data.remote.model.mapper

import ua.shtest.casestudy.data.remote.model.ItemApiModel
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
 * @author Stanislav Humeniuk on 02.09.2023
 * @email stanislav.humeniuk@gmail.com
 */

@Singleton
class ItemsApiMapper @Inject constructor() {

    fun map(
        itemApiModel: ItemApiModel,
        name: String = generateName(itemApiModel.pkDevice.toInt())
    ): Item {
        return Item(
            accountId = AccountId(itemApiModel.pkAccount),
            serialNumber = SerialNumber(itemApiModel.pkDevice),
            deviceType = DeviceType(itemApiModel.pkDeviceType),
            deviceSubType = DeviceSubType(itemApiModel.pkDeviceSubType),
            name = name,
            macAddress = itemApiModel.macAddress,
            internalIp = itemApiModel.internalIp,
            firmware = itemApiModel.firmware,
            platform = Platform.createFrom(itemApiModel.platform),
            model = itemApiModel.platform,
            lastAliveReported = itemApiModel.lastAliveReported.convertServerDateToLocal(),
            serverInfo = ItemServerInfo(
                device = itemApiModel.serverDevice,
                account = itemApiModel.serverAccount,
                event = itemApiModel.serverEvent
            )
        )
    }

    fun map(itemsApiModels: List<ItemApiModel>): List<Item> =
        itemsApiModels.mapIndexed { index, apiModel ->
            return@mapIndexed map(apiModel, generateName(index))
        }

    private fun generateName(index: Int): String {
        return "Home Number $index"
    }
}