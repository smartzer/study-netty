import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.Instant;

/**
 * @author: zhangzhen
 * @since: 2018-05-16 15:25
 */
public class TimeServerHandlerNetty extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String request = (String) msg;
        String response = null;
        if ("QUERY TIME ORDER".equals(request)) {
            response = Instant.now().toString();
        } else {
            response = "BAD REQUEST";
        }
        response = response + System.getProperty("line.separator");
        ByteBuf byteBuf = Unpooled.copiedBuffer(response.getBytes());
        ctx.writeAndFlush(byteBuf);
    }
}
