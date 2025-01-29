import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver {
    public static void main(String[] args) {
        String multicastAddress = "230.0.0.1";
        int port = 4446;

        try (MulticastSocket socket = new MulticastSocket(port)) {
            InetAddress group = InetAddress.getByName(multicastAddress);
            socket.joinGroup(group);

            System.out.println("In attesa di messaggi dal gruppo "
                    + multicastAddress + " su porta " + port + " ...");

            while(true) {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);

                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());

                System.out.println("Ricevuto: " + received);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}