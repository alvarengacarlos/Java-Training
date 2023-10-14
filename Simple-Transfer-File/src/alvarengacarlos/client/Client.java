package alvarengacarlos.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private String hostname = "127.0.0.1";
    private Integer port = 8000;
    private File file = new File("./client/file.txt");

    public void run() {
        connect();
    }

    private void connect() {
        try (Socket client = new Socket()) {
            InetSocketAddress endpoint = new InetSocketAddress(hostname, port);
            client.connect(endpoint);
            System.out.println("Connected");

            sendFile(client);

        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private void sendFile(Socket client) {
        try (InputStream inputStream = new FileInputStream(file)) {
            Integer value = 0;
            while(value != -1) {
                value = inputStream.read();
                client.getOutputStream().write(value);
            }

            System.out.println("Sent");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
