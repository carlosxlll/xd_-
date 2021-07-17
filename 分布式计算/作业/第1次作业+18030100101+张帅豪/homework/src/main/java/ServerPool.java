import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerPool {
    public static void main(String[] args) throws IOException {
        ServerSocket listenSocket = new ServerSocket(8189);
        Socket socket;
        System.out.println("Server listening at 8189");

        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(5, 10, 200,
                        TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        int cnt = 0;

        for (int i = 0; i < 15; i++) {
            socket = listenSocket.accept();
            ServerThread thread = new ServerThread(socket);
            executor.execute(thread);
            cnt++;
            System.out.println("The total number of clients is " + cnt + ".");
        }
        listenSocket.close();
    }
}


class ServerThread implements Runnable {
    Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        InputStream is=null;
        InputStreamReader isr=null;
        BufferedReader br=null;
        OutputStream os=null;
        PrintWriter pw=null;
        try {
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            String info=null;
            while((info=br.readLine())!=null){
                System.out.println("Message from client:"+info);
                pw.println(info);
                pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(pw!=null)
                    pw.close();
                if(os!=null)
                    os.close();
                if(br!=null)
                    br.close();
                if(isr!=null)
                    isr.close();
                if(is!=null)
                    is.close();
                if(socket!=null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
