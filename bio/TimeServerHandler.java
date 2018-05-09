import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 * @author: zhangzhen
 * @since: 2018-05-09 20:16
 */
public class TimeServerHandler implements Runnable{

    private Socket clientProxy;

    public TimeServerHandler(Socket clientProxy) {
        this.clientProxy = clientProxy;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            reader = new BufferedReader(new InputStreamReader(clientProxy.getInputStream()));
            writer = new PrintWriter(clientProxy.getOutputStream());
            // 因为每一个client 可以发送多次请求，所以这里的每一次循环，相当于接收处理一次请求
            while (true){
                String request = reader.readLine();
                if(!"GET CURRENT TIME".equals(request)){
                    writer.println("BAD_REQUEST");
                }else {
                    writer.println(LocalDateTime.now());
                }
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if (reader != null) {
                    reader.close();
                }
                writer.close();
                clientProxy.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
