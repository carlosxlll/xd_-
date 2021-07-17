import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.*;

public class EchoServerPool {
    public static void main(String[] args)throws Exception{
        Socket clientSocket;
        //建立连接，创建服务
        ServerSocket listenSocket = new ServerSocket(8189);//开放端口
        System.out.println("Server listening at 8189");


        //线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10,
                200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
        clientSocket = listenSocket.accept();
        executor.execute(new MyThreadPool("线程1",clientSocket));
        executor.execute(new MyThreadPool("线程2",clientSocket));
        executor.execute(new MyThreadPool("线程3",clientSocket));
        executor.execute(new MyThreadPool("线程4",clientSocket));
        executor.shutdown();
        listenSocket.close();

    }

}

class MyThreadPool implements Runnable {
    private String taskNum;
    private Socket clientSocket;
    InputStream inStream = null;
    OutputStream outStream = null;
    BufferedReader in = null;
    PrintWriter out = null;
    String line = null;

    public MyThreadPool(String name,Socket clientSocket) {
        this.taskNum = name;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run(){

        System.out.println(taskNum + "is running!");

        try{
            inStream = clientSocket.getInputStream();
            outStream = clientSocket.getOutputStream();
            in = new BufferedReader(new InputStreamReader(inStream));
            out = new PrintWriter(outStream);

            line = null;
            while((line=in.readLine())!=null) {
                System.out.println("Message from client:" + line);
                out.println(line);
                out.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try {
                if(out!=null)
                    out.close();
                if(outStream!=null)
                    outStream.close();
                if(in!=null)
                    in.close();
                if(inStream!=null)
                    inStream.close();
                if(clientSocket!=null)
                    clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}