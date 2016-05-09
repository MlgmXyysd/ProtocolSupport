package protocolsupport.protocol.packet.middlepacket.clientbound.play;

import java.io.IOException;

import net.minecraft.server.v1_9_R1.ItemStack;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middlepacket.ClientBoundMiddlePacket;

public abstract class MiddleEntityEquipment<T> extends ClientBoundMiddlePacket<T> {

	protected int entityId;
	protected int slot;
	protected ItemStack itemstack;

	@Override
	public void readFromServerData(PacketDataSerializer serializer) throws IOException {
		entityId = serializer.readVarInt();
		slot = serializer.readVarInt();
		itemstack = serializer.readItemStack();
	}

}