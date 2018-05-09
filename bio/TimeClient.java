import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author: zhangzhen
 * @since: 2018-05-09 20:33
 */
public class TimeClient {

    public static void main(String[] args) {
        BufferedReader reader = null;
        PrintWriter writer = null;
        Socket client = null;

        try {
            client = new Socket("127.0.0.1",8080);
            writer = new PrintWriter(client.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (true){
                // 每隔5秒钟发一次请求
                writer.println("GET CURRENT TIME");
                writer.flush();
                String response = reader.readLine();
                System.out.println("CURRENT TIME:"+response);
                Thread.sleep(5000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }finally{
            try {
                if (writer != null) {
                    writer.close();
                }
                if (client != null) {
                    client.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
