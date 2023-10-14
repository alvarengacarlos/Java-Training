package alvarengacarlos.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Integer port = 8000;
    private String hostname = "127.0.0.1";
    private File file = new File("./server/file.txt");

    public void run() {
        start();
    }

    private void start() {
        try (ServerSocket server = new ServerSocket()) {
            System.out.println("server is listen");
            listen(server);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void listen(ServerSocket server) throws IOException {
        InetSocketAddress endpoint = new InetSocketAddress(hostname, port);
        server.bind(endpoint);

        while (true) {
            Socket client = server.accept();
            System.out.printf("%s:%d connected\n", client.getInetAddress().getHostAddress(), client.getPort());

            saveFile(client);
        }
    }

    private void saveFile(Socket client) {
        createServerDir();
        try (OutputStream outputStream = new FileOutputStream(file)) {
            Integer value = 0;
            while(value != -1) {
                value = client.getInputStream().read();
                outputStream.write(value.byteValue());
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void createServerDir() {
        File dir = new File("./server");
        dir.mkdir();
    }
}
