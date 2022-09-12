package dobby;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import dobby.commands.Command;


/**
 * Dobby is a chat-bot that helps user track their daily tasks.
 */
public class Dobby {
    private static String filePath;
    private static DobbyList dobbyList;
    private static String defaultFilePath = "./data/dobbyList.txt";
    private UserInput ui;
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Constructor for Dobby class.
     *
     * @param filePath Path of file to be used for storing tasks.
     */
    public Dobby(String filePath) {
        dobbyList = new DobbyList();
        ui = new UserInput();
        this.filePath = filePath;
        DobbyStorage.load(dobbyList, filePath);
    }

    public Dobby() {
        this(defaultFilePath);
    }

    public static String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the output stream.
     *
     * @param input the input stream
     * @return the output stream
     */
    public String getResponse(String input) {
        outputStream.reset();
        try {
            UserInput ui = new UserInput();
            String commandStr = ui.readCommand(input);
            Command command = Parser.parse(commandStr);
            command.execute(dobbyList, ui);
            if (command.isBye()) {
                return "bye";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toString(StandardCharsets.UTF_8);
    }

    /**
     * Set the output stream for the Duke program.
     */
    public void setOut() {
        System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
    }

    /**
     * Gets the greeting message.
     */
    public String getGreetings() {
        outputStream.reset();
        DobbyChat.getHello();
        return outputStream.toString(StandardCharsets.UTF_8);
    }

    /**
     * Gets the list of commands.
     */
    public String getCommands() {
        outputStream.reset();
        DobbyChat.getCommands();
        return outputStream.toString(StandardCharsets.UTF_8);
    }
}
