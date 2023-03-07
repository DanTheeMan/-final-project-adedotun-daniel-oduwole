
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EchoServer extends AbstractServer {
    //Class variables *************************************************

    /**
     * The default port to listen on.
     */
    final public static int DEFAULT_PORT = 5555;

    //Constructors ****************************************************
    /**
     * Constructs an instance of the echo server.
     *
     * @param port The port number to connect on.
     */
    public EchoServer() {

        super(DEFAULT_PORT);

    }

    public EchoServer(int port) {
        super(port);

        try {
            this.listen(); //Start listening for connections
        } catch (Exception ex) {
            System.out.println("ERROR - Could not listen for clients!");
        }
    }

//    EchoServer(int port, ServerConsole aThis) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    //Instance methods ************************************************
    /**
     * This method handles any messages received from the client.
     *
     * @param msg The message received from the client.
     * @param client The connection from which the message originated.
     */
    public void handleMessageFromClient(Object msg, ConnectionToClient client) {
        System.out.println(msg);
        if (msg instanceof Envelope) {
            Envelope env = (Envelope) msg;
            System.out.println(env.getId());
            System.out.println(env.getContents().toString());
            handleCommandFromClient(env, client);
        } else {
            System.out.println("Message received: " + msg + " from " + client);

            String userId = client.getInfo("userId").toString();

            this.sendToAllClientsInRoom(userId + ": " + msg, client);
        }

    }

    public void handleCommandFromClient(Envelope env, ConnectionToClient client) {

        if (env.getId().equals("login")) {
            String userId = env.getContents().toString();
            client.setInfo("userId", userId);
            client.setInfo("room", "lobby");

        }
        if (env.getId().equals("join")) {
            String roomName = env.getContents().toString();
            client.setInfo("room", roomName);
        }

        if (env.getId().equals("pm")) {
            String target = env.getArg();
            String message = env.getContents().toString();
            sendToAClient(message, target, client);

        }
        if (env.getId().equals("yell")) {

            String message = env.getContents().toString();
            String userId = client.getInfo("userId").toString();
            this.sendToAllClients(userId + " yells: " + message);

        }
        if (env.getId().equals("who")) {
            sendRoomListToClient(client);

        }
        if (env.getId().equals("ftpUpload")) {
            try {
                String fileName = env.getArg();
                byte[] fileBytes = (byte[]) env.getContents();
                Path filePath = Paths.get("uploads", fileName);
                Files.write(filePath, fileBytes);
                System.out.println("File " + fileName + " saved to " + filePath.toAbsolutePath().toString());
                // Update the file list combo box
                
            } catch (IOException e) {
                System.err.println("Error saving file " + env.getArg() + ": " + e.getMessage());
            }
        }

        if (env.getId().equals("#ftplist")) {
            String[] fileList = (String[]) env.getContents();
            
        }
        if (env.getId().equals("ftpget")) {
            String fileName = env.getArg();
            String filePath = "files/" + fileName;

            File fileToSend = new File(filePath);
            if (fileToSend.isFile()) {
                try {
                    client.sendToClient(new Envelope("ftpget", fileName, Files.readAllBytes(fileToSend.toPath())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendFileListToClient(ConnectionToClient client) {
        File folder = new File("ftp");
        File[] listOfFiles = folder.listFiles();
        String[] fileNames = new String[listOfFiles.length];
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                fileNames[i] = listOfFiles[i].getName();
            }
        }
        Envelope env = new Envelope("ftplist", "", fileNames);
        try {
            client.sendToClient(env);
        } catch (IOException e) {
            System.out.println("Failed to send file list to client.");
            e.printStackTrace();
        }
    }

    public void setPort() {

    }

    public void sendRoomListToClient(ConnectionToClient client) {
        Envelope env = new Envelope();
        env.setId("who");
        ArrayList<String> userList = new ArrayList<String>();
        String room = client.getInfo("room").toString();
        env.setArg(room);

        Thread[] clientThreadList = getClientConnections();
        for (int i = 0; i < clientThreadList.length; i++) {
            ConnectionToClient target = (ConnectionToClient) clientThreadList[i];
            if (target.getInfo("room").equals(room)) {
                userList.add(target.getInfo("userId").toString());
            }
        }

        env.setContents(userList);

        try {
            client.sendToClient(env);
        } catch (Exception e) {
            System.out.println("failed to send userlist to client");
        }
    }

    public void sendToAllClientsInRoom(Object msg, ConnectionToClient client) {
        Thread[] clientThreadList = getClientConnections();
        String room = client.getInfo("room").toString();

        for (int i = 0; i < clientThreadList.length; i++) {
            ConnectionToClient target = (ConnectionToClient) clientThreadList[i];
            if (target.getInfo("room").equals(room)) {
                try {
                    target.sendToClient(msg);
                } catch (Exception ex) {
                    System.out.println("failed to send to client");
                }
            }
        }
        File[] files = new File("uploads").listFiles();
        List<String> fileList = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(file.getName());
                }
            }
        }
        Envelope env = new Envelope("update_list", "", fileList);
        sendToAllClients(env);
    }

    public void sendToAClient(Object msg, String pmTarget, ConnectionToClient client) {
        Thread[] clientThreadList = getClientConnections();
        String userId = client.getInfo("userId").toString();
        String room = client.getInfo("room").toString();

        for (int i = 0; i < clientThreadList.length; i++) {
            ConnectionToClient target = (ConnectionToClient) clientThreadList[i];
            if (target.getInfo("userId").equals(pmTarget)) {
                try {
                    target.sendToClient(userId + ": " + msg);
                } catch (Exception ex) {
                    System.out.println("failed to send private message");
                }
            }
        }
    }

    /**
     * This method overrides the one in the superclass. Called when the server
     * starts listening for connections.
     */
    protected void serverStarted() {
        System.out.println("Server listening for connections on port " + getPort());
    }

    /**
     * This method overrides the one in the superclass. Called when the server
     * stops listening for connections.
     */
    protected void serverStopped() {
        System.out.println("Server has stopped listening for connections.");
    }

    //Class methods ***************************************************
    /**
     * This method is responsible for the creation of the server instance (there
     * is no UI in this phase).
     *
     * @param args[0] The port number to listen on. Defaults to 5555 if no
     * argument is entered.
     */
    public static void main(String[] args) {
        int port = 0; //Port to listen on

        try {
            port = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException oob) {
            port = DEFAULT_PORT;
        }
        port = DEFAULT_PORT; //Set port to 5555

        EchoServer sv = new EchoServer(port);

        try {
            sv.listen(); //Start listening for connections
        } catch (Exception ex) {
            System.out.println("ERROR - Could not listen for clients!");
        }

    }

    protected void clientConnected(ConnectionToClient client) {

        System.out.println("<Client Connected:" + client + ">");
        client.setInfo("room", "lobby");
        client.setInfo("userId", "guest");

    }

    synchronized protected void clientException() {
        System.out.println("Client shutdown");
    }

}
//End of EchoServer class
