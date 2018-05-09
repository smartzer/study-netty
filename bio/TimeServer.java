import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: zhangzhen
 * @since: 2018-05-09 20:11
 */
public class TimeServer {

    public static void main(String[] args) {
        ServerSocket server = null;

        try {
            server = new ServerSocket(8080);
            System.out.println("time server started at port 8080...");
            while (true) {
                Socket client = server.accept();
                // 每次接收到一个新的客户端连接，启动一个新的线程来处理
                new Thread(new TimeServerHandler(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (server != null) {
                    server.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
