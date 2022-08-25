package dobby;

import dobby.commands.Command;

import java.util.Scanner;
import java.io.IOException;


/**
 * Dobby is a chat-bot that helps user track their daily tasks.
 */
public class Dobby {
    private static Scanner scanner = new Scanner(System.in);
    private static DobbyList dobbyList;
    private static UserInput ui;
    private static String filePath;

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

    /**
     * Starts the program.
     *
     * @throws IOException
     */
    private static void dobbyStart() throws IOException {
        DobbyChat.sayHello();
        boolean isBye = false;

        while (!isBye) {
            String task = ui.readCommand();
            Command cmd = Parser.parse(task);
            cmd.execute(dobbyList, ui);
            isBye = cmd.isBye();
        }
    }
    public static String getFilePath() {
        return filePath;
    }

    /**
     * Main method that runs the program.
     */
    public static void main(String[] args) throws IOException {
        new Dobby("./data/dobbyList.txt").dobbyStart();
    }
}