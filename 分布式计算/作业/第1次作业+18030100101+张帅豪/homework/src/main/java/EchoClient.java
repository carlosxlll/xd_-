import java.io.*;
import java.net.*;

public class EchoClient {

    public static void main(String[] args) throws Exception {

        String userInput = null;
        String echoMessage = null;

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        Socket socket = new Socket("127.0.0.1", 8189);
        System.out.println("Connected to Server");

        InputStream inStream = socket.getInputStream();
        OutputStream outStream = socket.getOutputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        PrintWriter out = new PrintWriter(outStream);


        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            out.flush();
            echoMessage = in.readLine();
            System.out.println("Echo from server: " + echoMessage);
        }

        socket.close();

    }
}