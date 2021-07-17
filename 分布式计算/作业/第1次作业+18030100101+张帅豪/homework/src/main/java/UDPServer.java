import java.io.IOException;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        int cnt = 0;
        DatagramSocket socket = null;
        DatagramPacket packet;
        byte[] data = new byte[1024];
        try {
            socket = new DatagramSocket(6789);
            packet = new DatagramPacket(data, data.length);
            while (true) {
                socket.receive(packet);
                UDPServerThread thread1 = new UDPServerThread("线程一",data, socket, packet);
                UDPServerThread thread2 = new UDPServerThread("线程二",data, socket, packet);
                thread1.start();
                thread2.start();

                System.out.println("the number of client: " + ++cnt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null)
                socket.close();
        }
    }
}
