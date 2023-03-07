
import java.util.ArrayList;
import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JComboBox;

/**
 * This class overrides some of the methods defined in the abstract superclass
 * in order to give more functionality to the client.
 */
public class ChatClient extends AbstractClient {
    //Instance variables **********************************************

    /**
     * The interface type variable. It allows the implementation of the display
     * method in the client.
     */
    ChatIF clientUI;

    //Constructors ****************************************************
    /**
     * Constructs an instance of the chat client.
     *
     * @param host The server to connect to.
     * @param port The port number to connect on.
     * @param clientUI The interface type variable.
     */
    public ChatClient(String host, int port, ChatIF clientUI)
            throws IOException {
        super(host, port); //Call the superclass constructor
        this.clientUI = clientUI;
        openConnection();
    }

    //Instance methods ************************************************
    /**
     * This method handles all data that comes in from the server.
     *
     * @param msg The message from the server.
     */
    public void handleMessageFromServer(Object msg) {

        if (msg instanceof Envelope) {
            Envelope env = (Envelope) msg;
            String command = env.getId();
            if (command.equals("ftpget")) {
                byte[] fileContent = (byte[]) env.getContents();
                String filename = env.getArg();
                Path filePath = Paths.get("downloads/" + filename);
                try {
                    Files.write(filePath, fileContent);
                    System.out.println("File " + filename + " downloaded successfully");
                } catch (IOException e) {
                    System.err.println("Error writing file " + filename + " to disk: " + e.getMessage());
                }
            }
            handleCommandFromServer(env);
        } else {

        }

        clientUI.display(msg.toString());
    }

   

    public void handleCommandFromServer(Envelope env) {
        if (env.getId().equals("#ftpUpload")) {
            String fileName = env.getArg();
            byte[] fileContents = (byte[]) env.getContents();
            Path path = Paths.get("C:\\Users\\leoli\\uploads");
            try {
                if (!Files.exists(path)) {
                    Files.createDirectory(path);
                    System.out.println("Created uploads directory");
                }
            } catch (IOException ex) {
                System.err.println("Error creating directory: " + ex.getMessage());
            }
            Path filePath = Paths.get("C:\\Users\\leoli\\uploads", fileName);
            try {
                Files.write(filePath, fileContents);
                System.out.println("File saved: " + fileName);
                env = new Envelope("#ftpList", fileName, fileContents);
                handleCommandFromServer(env);
            } catch (IOException ex) {
                System.err.println("Error saving file: " + ex.getMessage());
            }

        }
        
        if (env.getId().equals("#ftpget")) {
            String fileName = env.getArg();
            byte[] fileContents = (byte[]) env.getContents();
            Path path = Paths.get("C:\\Users\\leoli\\Downloads");
            
            Path filePath = Paths.get("C:\\Users\\leoli\\Downloads", fileName);
            try {
                Files.write(filePath, fileContents);
                System.out.println("File downloaded: " + fileName);
            } catch (IOException ex) {
                System.err.println("Error saving file: " + ex.getMessage());
            }

        }
        if (env.getId().equals("#ftplist")) {
            GUIConsole c = new GUIConsole();
            ArrayList<Envelope> envelopeList = new ArrayList<>();
            Path path = Paths.get("C:\\Users\\leoli\\uploads");
            try {
                if (!Files.exists(path)) {
                    Files.createDirectory(path);
                    System.out.println("Created uploads directory");
                }
                DirectoryStream<Path> stream = Files.newDirectoryStream(path);
                for (Path file : stream) {
                    envelopeList.add(env);
                    c.updateFileListDropdown(env);
                }

            } catch (IOException ex) {
                System.err.println("Error listing files: " + ex.getMessage());
            }

        }
        if (env.getId().equals("who")) {
            ArrayList<String> userList = (ArrayList<String>) env.getContents();
            String room = env.getArg();
            clientUI.display("Users in " + room);
            for (String s : userList) {
                clientUI.display(s);
            }
        }

    }

    /**
     * This method handles all data coming from the UI
     *
     * @param message The message from the UI.
     */
    public void handleMessageFromClientUI(String message) throws IOException {

        if (message.charAt(0) == '#') {

            handleClientCommand(message);

        } else {
            try {
                sendToServer(message);
            } catch (IOException e) {
                clientUI.display("Could not send message to server.  Terminating client.......");
                quit();
            }
        }
    }

    /**
     * This method terminates the client.
     */
    public void quit() {
        try {
            closeConnection();
        } catch (IOException e) {
        }
        System.exit(0);
    }

    public void connectionClosed() {

        System.out.println("Connection closed");

    }

    public void handleClientCommand(String message) throws IOException {

        if (message.equals("#quit")) {
            clientUI.display("Shutting Down Client");
            quit();

        }

        if (message.equals("#logoff")) {
            clientUI.display("Disconnecting from server");
            try {
                closeConnection();
            } catch (IOException e) {
            };

        }
        //#setHost 192.168.0.1
        if (message.indexOf("#setHost") >= 0) {

            if (isConnected()) {
                clientUI.display("Cannot change host while connected");
            } else {
                setHost(message.substring(8, message.length()).trim());
            }

        }
        //#setPort12345
        if (message.indexOf("#setPort") >= 0) {

            if (isConnected()) {
                clientUI.display("Cannot change port while connected");
            } else {
                setPort(Integer.parseInt(message.substring(8, message.length()).trim()));
            }

        }

        //#Login username
        // #login username
        if (message.indexOf("#login") == 0) {

            if (isConnected()) {
                clientUI.display("already connected");
            } else {

                try {
                    openConnection();
                    String userName = message.substring(6, message.length()).trim();

                    Envelope env = new Envelope("login", "", userName);
                    this.sendToServer(env);

                } catch (IOException e) {
                    clientUI.display("failed to connect to server.");
                }
            }
        }
        //#join lobby
        if (message.indexOf("#join") == 0) {
            try {
                String roomName = message.substring(5, message.length()).trim();
                Envelope env = new Envelope("join", "", roomName);
                this.sendToServer(env);
            } catch (IOException e) {
                clientUI.display("failed to join a room");
            }
        }
        //pm Nick message
        if (message.indexOf("#pm") == 0) {
            try {
                // nick message
                String targetAndMessage = message.substring(3, message.length()).trim();
                //nick
                String target = targetAndMessage.substring(0, targetAndMessage.indexOf(" ")).trim();
                //message
                String pm = targetAndMessage.substring(targetAndMessage.indexOf(" "), targetAndMessage.length()).trim();

                Envelope env = new Envelope("pm", target, pm);
                this.sendToServer(env);
            } catch (IOException e) {
                clientUI.display("could not private message user");
            }

        }
        //#yell OH MY GOD IM FREAKING OUT
        if (message.indexOf("#yell") == 0) {

            try {
                String yellMessage = message.substring(5, message.length()).trim();
                Envelope env = new Envelope("yell", "", yellMessage);
                this.sendToServer(env);
            } catch (IOException e) {
                System.out.println("failed to yell");
            }

        }

        if (message.equals("#who")) {
            try {
                Envelope env = new Envelope("who", "", "");
                this.sendToServer(env);
            } catch (Exception e) {
                clientUI.display("failed to aquire user list");
            }

        }
        if (message.indexOf("#ftpupload") == 0) {
            Envelope env = new Envelope();
            env.setId("ftpUpload");
            String fileName = message.split(" ")[1];
            byte[] fileBytes = (byte[]) env.getContents();
            try {
                File uploadsDir = new File("uploads");
                if (!uploadsDir.exists()) {
                    uploadsDir.mkdir();
                }
                File file = new File(uploadsDir.getPath() + "/" + fileName);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(fileBytes);
                fileOutputStream.flush();
                fileOutputStream.close();
                clientUI.display("File " + fileName + " saved successfully in uploads folder");
            } catch (IOException e) {
                clientUI.display("Error saving file " + fileName);
            }
        }

        if (message.indexOf("#ftpget") == 0) {
            String fileName = message.split(" ")[1];
            Envelope env = new Envelope();
            env.setId("ftpGet");
            env.setContents(fileName);
            this.sendToServer(env);
        }

        //#ftplist
        if (message.equals("#ftplist")) {
            Envelope env = new Envelope();
            env.setId("ftpList");
            this.sendToServer(env);
        }

    }

    protected void connectionException(Exception exception) {
        clientUI.display("Server has shutdown");
    }

}
//End of ChatClient class
