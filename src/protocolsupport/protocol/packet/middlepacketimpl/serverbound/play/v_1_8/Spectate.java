package protocolsupport.protocol.packet.middlepacketimpl.serverbound.play.v_1_8;

import java.io.IOException;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.packet.middlepacket.serverbound.play.MiddleSpecate;

public class Spectate extends MiddleSpecate {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) throws IOException {
		entityUUID = serializer.readUUID();
	}

}