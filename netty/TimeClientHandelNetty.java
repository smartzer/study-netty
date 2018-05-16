import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: zhangzhen
 * @since: 2018-05-16 15:49
 */
public class TimeClientHandelNetty extends ChannelInboundHandlerAdapter {
    private byte[] req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ByteBuf message = Unpooled.buffer(req.length);
        message.writeBytes(req);
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("Now is:" + body);
    }
}
