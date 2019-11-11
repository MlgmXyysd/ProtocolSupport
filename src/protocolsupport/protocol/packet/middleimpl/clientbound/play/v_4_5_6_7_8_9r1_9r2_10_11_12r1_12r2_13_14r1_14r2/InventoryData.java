package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ConnectionImpl;
import protocolsupport.protocol.packet.PacketType;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleInventoryData;
import protocolsupport.protocol.packet.middleimpl.ClientBoundPacketData;
import protocolsupport.protocol.packet.middleimpl.IPacketData;
import protocolsupport.protocol.storage.netcache.window.WindowEnchantmentCache;
import protocolsupport.protocol.storage.netcache.window.WindowFurnaceCache;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableEmptyList;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class InventoryData extends MiddleInventoryData {

	protected static final int ENCHANTMENT_TYPE_ID_TOP = 4;
	protected static final int ENCHANTMENT_TYPE_ID_MIDDLE = 5;
	protected static final int ENCHANTMENT_TYPE_ID_BOTTOM = 6;
	protected static final int ENCHANTMENT_TYPE_LEVEL_TOP = 7;
	protected static final int ENCHANTMENT_TYPE_LEVEL_MIDDLE = 8;
	protected static final int EHCNAHTMENT_TYPE_LEVEL_BOTTOM = 9;

	protected static final int FURNACE_TYPE_FUEL_TIME_CURRENT = 0;
	protected static final int FURNACE_TYPE_FUEL_TIME_MAX = 1;
	protected static final int FURNACE_TYPE_PROGRESS_CURRENT = 2;
	protected static final int FURNACE_TYPE_PROGRESS_MAX = 3;

	public InventoryData(ConnectionImpl connection) {
		super(connection);
	}

	@Override
	public RecyclableCollection<? extends IPacketData> toData() {
		switch (windowCache.getOpenedWindowType()) {
			case ENCHANTMENT: {
				if (version.isBetween(ProtocolVersion.MINECRAFT_1_9_4, ProtocolVersion.MINECRAFT_1_8)) {
					WindowEnchantmentCache enchantmentCache = (WindowEnchantmentCache) windowCache.getOpenedWindowMetadata();
					if ((type >= ENCHANTMENT_TYPE_ID_TOP) && (type <= ENCHANTMENT_TYPE_ID_BOTTOM)) {
						return RecyclableSingletonList.create(create(
							windowId, type, enchantmentCache.updateEnchantmentId(type - ENCHANTMENT_TYPE_ID_TOP, value)
						));
					} else if ((type >= ENCHANTMENT_TYPE_LEVEL_TOP) && (type <= EHCNAHTMENT_TYPE_LEVEL_BOTTOM)) {
						return RecyclableSingletonList.create(create(
							windowId, type - 3, enchantmentCache.updateEnchantmentLevel(type - ENCHANTMENT_TYPE_LEVEL_TOP, value)
						));
					}
				}
				break;
			}
			case FURNACE: {
				if (version.isBefore(ProtocolVersion.MINECRAFT_1_8)) {
					WindowFurnaceCache furnaceCache = (WindowFurnaceCache) windowCache.getOpenedWindowMetadata();
					switch (type) {
						case FURNACE_TYPE_FUEL_TIME_CURRENT: {
							return RecyclableSingletonList.create(create(windowId, 1, furnaceCache.scaleCurrentFuelBurnTime(value)));
						}
						case FURNACE_TYPE_FUEL_TIME_MAX: {
							furnaceCache.setMaxFuelBurnTime(value);
							return RecyclableEmptyList.get();
						}
						case FURNACE_TYPE_PROGRESS_CURRENT: {
							return RecyclableSingletonList.create(create(windowId, 0, furnaceCache.scaleCurrentCurrentProgress(value)));
						}
						case FURNACE_TYPE_PROGRESS_MAX: {
							furnaceCache.setMaxProgress(value);
							return RecyclableEmptyList.get();
						}
					}
				}
				break;
			}
			default: {
				break;
			}
		}
		return RecyclableSingletonList.create(create(windowId, type, value));
	}

	protected static ClientBoundPacketData create(byte windowId, int type, int value) {
		ClientBoundPacketData serializer = ClientBoundPacketData.create(PacketType.CLIENTBOUND_PLAY_WINDOW_DATA);
		serializer.writeByte(windowId);
		serializer.writeShort(type);
		serializer.writeShort(value);
		return serializer;
	}

}