
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerConsole implements ChatIF {

    // Class variables
    final public static int DEFAULT_PORT = 5555;

    // Instance variables
    EchoServer server;

    // Constructors
    public ServerConsole() {
        try {
            server = new EchoServer();
        } catch (Exception e) {
            System.out.println("Error: Can't setup connection on port " + "!");
            System.exit(1);
        }
    }

    // Instance methods
    public void accept() {
        try {
            BufferedReader fromConsole = new BufferedReader(new InputStreamReader(System.in));
            String message;

            while (true) {
                Scanner input = new Scanner(System.in);
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
                    server.close();
                } else if (message.startsWith("#ison")) {
                    String[] args = message.split(" ");
                    ison(args[1]);
                } else if (message.equals("#userstatus")) {
                    userStatus();
                } else if (message.startsWith("#joinroom")) {
                    String[] args = message.split(" ");
                    joinroom(args[1], args[2]);
                } else if (message.equals("#quit")) {
                    System.exit(0);
                } else {
                    server.sendToAllClients("<ADMIN>" + message);
                }
            }
        } catch (Exception ex) {
            display("Unexpected error while reading from console!");
        }
    }

    public void ison(String user) {
        Thread[] clientThreadList = server.getClientConnections();
        for (int i = 0; i < clientThreadList.length; i++) {
            ConnectionToClient target = (ConnectionToClient) clientThreadList[i];
            if (user.equals(target.getInfo("userId").toString())) {
                System.out.println(user + " is on in room: " + target.getInfo("room").toString());

            } else {
                System.out.println(user + " is not logged in");
            }

        }
    }

    public void userStatus() {
        Thread[] clientThreadList = server.getClientConnections();
        for (int i = 0; i < clientThreadList.length; i++) {
            ConnectionToClient target = (ConnectionToClient) clientThreadList[i];
            System.out.println(target.getInfo("userId").toString() + " - " + target.getInfo("room").toString());
        }
    }

    public void joinroom(String roomname, String newroomname) {
        Thread[] clientThreadList = server.getClientConnections();
        for (int i = 0; i < clientThreadList.length; i++) {
            ConnectionToClient target = (ConnectionToClient) clientThreadList[i];
            if (roomname.equals(target.getInfo("room").toString())) {
                target.setInfo("room", newroomname);
            }
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
