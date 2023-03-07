/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leoli
 */
public class GUIConsole extends JFrame implements ChatIF {

    private JButton closeB = new JButton("Close");
    private JButton browseB = new JButton("Browse");
    private JButton sendB = new JButton("Send");
    private JButton loginB = new JButton("Login");
    private JButton logoffB = new JButton("Log off");
    private JButton saveB = new JButton("Save");
    private JButton downloadB = new JButton("Download"); // New Download button
    private ClientConsole clientConsole;
    private String fileName;
    private JComboBox<String> fileListCombo = new JComboBox<String>(); // New combo box
    private File selectedFile;

    public GUIConsole() throws HeadlessException {
    }

    private JButton quitB = new JButton("Quit");
    private JTextField portTxF = new JTextField("5555");
    private JTextField hostTxF = new JTextField("127.0.0.1");
    private JTextField messageTxF = new JTextField("");
    private JTextField LoginTxF = new JTextField("");
    private JLabel portLB = new JLabel("Port: ", JLabel.RIGHT);
    private JLabel hostLB = new JLabel("Host: ", JLabel.RIGHT);
    private JLabel messageLB = new JLabel("Message: ", JLabel.RIGHT);

    private JTextArea messageList = new JTextArea();

    public GUIConsole(String host, int port, ClientConsole clientConsole) {
        super("Simple Chat GUI");
        this.clientConsole = clientConsole; // save reference to ChatClient instance
        ChatClient client = clientConsole.getClient();
        setSize(400, 400);
        setLayout(new BorderLayout(5, 5));

        JPanel bottom = new JPanel(new GridLayout(2, 1, 5, 5));
        JPanel top = new JPanel(new GridLayout(2, 2, 5, 5));
        JPanel center = new JPanel(new FlowLayout()); // New center panel

        add("Center", messageList); // add center panel
        top.add(fileListCombo); // add combo box to center panel
        top.add(downloadB); // add Download button to center panel
        fileListCombo.setPreferredSize(new Dimension(240, 25));

        add("South", bottom);
        add("North", top);

        top.add(hostLB);
        top.add(hostTxF);
        hostTxF.setPreferredSize(new Dimension(120, 25));
        top.add(portLB);
        top.add(portTxF);
        portTxF.setPreferredSize(new Dimension(120, 25));

        bottom.add(sendB);
        sendB.setPreferredSize(new Dimension(120, 25));
        bottom.add(messageLB);
        bottom.add(messageTxF);
        messageTxF.setPreferredSize(new Dimension(240, 25));
        bottom.add(loginB);
        loginB.setPreferredSize(new Dimension(120, 25));
        bottom.add(LoginTxF);
        LoginTxF.setPreferredSize(new Dimension(240, 25));
        bottom.add(logoffB);
        logoffB.setPreferredSize(new Dimension(120, 25));
        bottom.add(saveB);
        saveB.setPreferredSize(new Dimension(120, 25));
        bottom.add(browseB);
        browseB.setPreferredSize(new Dimension(120, 25));
        bottom.add(closeB);
        closeB.setPreferredSize(new Dimension(120, 25));
        bottom.add(quitB);
        quitB.setPreferredSize(new Dimension(120, 25));

        setVisible(true);

        sendB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                send();
                display(messageTxF.getText() + "\n");
            }
        });

        downloadB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedFileName = (String) fileListCombo.getSelectedItem();
                if (selectedFileName != null) {
                    // Create envelope with #ftpget command and filename argument
                    Envelope env = new Envelope("#ftpget", selectedFileName,selectedFileName.getBytes());

                    // Send envelope to server
                    client.handleCommandFromServer(env);

                    // Save file to downloads directory
                    Path downloadsPath = Paths.get(System.getProperty("user.home"), "zzz");
                    try {
                        if (!Files.exists(downloadsPath)) {
                            Files.createDirectory(downloadsPath);
                        }
                        byte[] fileContents = (byte[]) env.getContents();
                        Path filePath = Paths.get(downloadsPath.toString(), selectedFileName);
                        Files.write(filePath, fileContents);
                        display("File " + selectedFileName + " downloaded successfully");
                    } catch (IOException ex) {
                        display("Error downloading file " + selectedFileName);
                    }
                } else {
                    display("Please select a file to download");
                }
            }
        });
        
        browseB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(fileChooser);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    String fileName = selectedFile.getName();
                    try {
                        byte[] fileBytes = Files.readAllBytes(selectedFile.toPath());
                        display("File " + fileName + " loaded successfully");

                        // Create an envelope with #ftpUpload command, filename argument, and file bytes data
                    } catch (IOException ex) {
                        display("Error loading file " + fileName);
                    }
                }
            }
        });

        saveB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedFile != null) {
                    try {
                        // Convert file to byte array
                        byte[] fileData = Files.readAllBytes(selectedFile.toPath());

                        // Create envelope with file data and send to server
                        Envelope envelope = new Envelope("#ftpUpload", selectedFile.getName(), fileData);
                        updateFileListDropdown(envelope);
                        client.handleCommandFromServer(envelope);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        loginB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Get the username from the text field in the GUIConsole
                String loginName = LoginTxF.getText();
                if (!loginName.isEmpty()) {

                    try {
                        client.handleMessageFromClientUI("#login " + loginName);
                    } catch (IOException ex) {
                        Logger.getLogger(GUIConsole.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    display("Logged in as: " + loginName);
                }

            }
        });
        logoffB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the username from the text field in the GUIConsole
                    client.handleMessageFromClientUI("#logoff");
                } catch (IOException ex) {
                    Logger.getLogger(GUIConsole.class.getName()).log(Level.SEVERE, null, ex);
                }
                display("> Disconnecting from server");
            }
        });
    }

    public void display(String message) {
        messageList.insert(message, 0);
    }

    private void send() {
        try {
            String message = messageTxF.getText();

            // Get the reference to the ChatClient instance from the GUIConsole constructor
            ChatClient client = clientConsole.getClient();

            // Check if the client is connected to the server before sending the message
            if (!client.isConnected()) {
                display("Error: not connected to server.");
                return;
            }

            // Send the message to the server
            client.handleMessageFromClientUI(message);
            messageTxF.setText("");
        } catch (Exception ex) {
            display("Error sending message: " + ex.getMessage());
        }
    }

    public void updateFileListDropdown(Envelope env) {
        // Add each file name to the file list dropdown
        
        fileListCombo.addItem(env.getArg());
    }

}
