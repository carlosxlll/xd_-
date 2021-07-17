import java.net.*;
import java.io.*;

public class UDPServer{
	public static void main(String args[]){
		DatagramSocket aSocket = null;
		DatagramPacket request;
		try{
			aSocket = new DatagramSocket(6789);
			byte[] buffer = new byte[1000];

			while(true){
				request = new DatagramPacket(buffer,buffer.length);
				aSocket.receive(request);
				UDPServerThread thread1 = new UDPServerThread(buffer, aSocket, request,1);
				UDPServerThread thread2 = new UDPServerThread(buffer, aSocket, request,2);
				UDPServerThread thread3 = new UDPServerThread(buffer, aSocket, request,3);
				UDPServerThread thread4 = new UDPServerThread(buffer, aSocket, request,4);
				thread1.start();
				thread2.start();
				thread3.start();
				thread4.start();
				System.out.println("主进程:  "+"Server receive: " + new String(buffer));
				DatagramPacket reply = new DatagramPacket(request.getData(),
						request.getLength(), request.getAddress(), request.getPort());
				aSocket.send(reply);

			}
		} catch (SocketException e){
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null) aSocket.close();
		}
	}
}