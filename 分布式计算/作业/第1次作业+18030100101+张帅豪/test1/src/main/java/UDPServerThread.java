import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServerThread extends Thread{
    private DatagramSocket socket;
    private DatagramPacket request;
    private byte[] buffer;
    private int number;

    public UDPServerThread(byte[] buffer, DatagramSocket socket,DatagramPacket request,int number) {
        this.buffer = buffer;
        this.socket = socket;
        this.request = request;
        this.number = number;

    }
    @Override
    public void run() {
        try {
            System.out.println("线程"+number+":  "+"Server receive: " + new String(buffer));
            System.out.println();
            DatagramPacket reply = new DatagramPacket(request.getData(),
                    request.getLength(), request.getAddress(), request.getPort());
            socket.send(reply);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
