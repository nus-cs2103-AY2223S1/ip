package dobby;

import dobby.commands.Command;

import java.util.Scanner;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;


/**
 * Dobby is a chat-bot that helps user track their daily tasks.
 */
public class Dobby {
    private static Scanner scanner = new Scanner(System.in);
    private static DobbyList dobbyList;
    private static UserInput ui;
    private static String filePath;
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
        this("./data/dobbyList.txt");
    }

    public static String getFilePath() {
        return filePath;
    }

    /**
     * Main method that runs the program.
     */
//    public static void main(String[] args) throws IOException {
//        new Dobby("./data/dobbyList.txt").dobbyStart();
//    }
    /**
     * Returns the output stream.
     * @param input the input stream
     * @return the output stream
     */
    public String getResponse(String input) {
        outputStream.reset();
        try {
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
     * Gets the help message.
     */
    public String getHelp() {
        outputStream.reset();
        DobbyChat.getHelp();
        return outputStream.toString(StandardCharsets.UTF_8);
    }
}