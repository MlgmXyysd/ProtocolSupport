package protocolsupport.protocol.packet.middlepacketimpl.serverbound.play.v_1_4_1_5_1_6;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middlepacket.serverbound.play.MiddleClientSettings;

public class ClientSettings extends MiddleClientSettings {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) {
		locale = serializer.readString(7);
		viewDist = serializer.readByte();
		int chatState = serializer.readByte();
		chatMode = chatState & 7;
		chatColors = (chatState & 8) == 8;
		serializer.readByte();
		serializer.readBoolean();
		skinFlags = 255;
		mainHand = 1;
	}

}