

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerConsole implements ChatIF {

    // Class variables
    final public static int DEFAULT_PORT = 2222;

    // Instance variables
    EchoServer server;

    // Constructors
    public ServerConsole() {
        try {
            server = new EchoServer();
        } catch (Exception e) {
            System.out.println("Error: Can't setup connection on port " + port + "!");
            System.exit(1);
        }
    }

    // Instance methods
    public void accept() {
        try {
            BufferedReader fromConsole = new BufferedReader(new InputStreamReader(System.in));
            String message;

            while (true) {
                message = fromConsole.readLine();

                if (message.startsWith("#setPort")) {
                    String[] args = message.split(" ");
                    if (args.length == 2) {
                        try {
                            int port = Integer.parseInt(args[1]);
                            server.setPort(port);
                            display("Server port set to " + port);
                        } catch (NumberFormatException e) {
                            display("Invalid port number.");
                        }
                    } else {
                        display("Usage: #setPort <port number>");
                    }
                } else if (message.equals("#start")) {
                    server.listen();
                } else if (message.equals("#stop")) {
                    server.stopListening();
                } else if (message.equals("#quit")) {
                    System.exit(1);
                } else {
                    server.sendToAllClients("<ADMIN>" + message);
                }
            }
        } catch (Exception ex) {
            display("Unexpected error while reading from console!");
        }
    }

    public void display(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {

        ServerConsole console = new ServerConsole();
        console.accept();
    }
}
//End of ConsoleChat class
