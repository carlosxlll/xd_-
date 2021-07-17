import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServerThread extends Thread {
    private DatagramSocket socket;
    private DatagramPacket packet;
    private byte[] data;
    private String name;
    public UDPServerThread(String name,byte[] data, DatagramSocket socket, DatagramPacket packet) {
        this.name = name;
        this.data = data;
        this.socket = socket;
        this.packet = packet;
    }

    @Override
    public void run() {
        try {
            System.out.println(name+"启动"+"Server receive: " + new String(data));
            DatagramPacket ret = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
            socket.send(packet);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
