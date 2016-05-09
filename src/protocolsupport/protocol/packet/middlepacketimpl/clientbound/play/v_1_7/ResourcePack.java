package protocolsupport.protocol.packet.middlepacketimpl.clientbound.play.v_1_7;

import java.nio.charset.StandardCharsets;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.packet.ClientBoundPacket;
import protocolsupport.protocol.packet.middlepacket.clientbound.play.MiddleResourcePack;
import protocolsupport.protocol.packet.middlepacketimpl.PacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class ResourcePack extends MiddleResourcePack<RecyclableCollection<PacketData>> {

	@Override
	public RecyclableCollection<PacketData> toData(ProtocolVersion version) {
		PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_CUSTOM_PAYLOAD_ID, version);
		serializer.writeString("MC|RPack");
		serializer.writeArray(url.getBytes(StandardCharsets.UTF_8));
		return RecyclableSingletonList.create(serializer);
	}

}