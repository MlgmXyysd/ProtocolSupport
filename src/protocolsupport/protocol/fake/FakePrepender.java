package protocolsupport.protocol.fake;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.minecraft.server.v1_8_R2.Packet;
import net.minecraft.server.v1_8_R2.PacketListener;

public class FakePrepender extends MessageToByteEncoder<Packet<PacketListener>> {

	@Override
	protected void encode(ChannelHandlerContext arg0, Packet<PacketListener> arg1, ByteBuf arg2) throws Exception {
	}

}
