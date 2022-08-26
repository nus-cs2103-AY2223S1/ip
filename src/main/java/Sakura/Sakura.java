package Sakura;

import java.util.Scanner;

/**
 * Represents the Sakura chatbot object.
 */
public class Sakura {
    static boolean inProgress = true;
    public static TaskList taskList;
    public static Ui ui;
    public static Storage storage;

    /**
     * Constructor for Sakura
     *
     * @param filePath the path to an existing txt database file or the path to save the new txt database file
     */
    public Sakura(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        taskList = new TaskList(storage.loadData());
    }

    /**
     * Starts Sakura chatbot.
     */
    private void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        while (inProgress) {
            String command = sc.nextLine();
            Parser.parseCommand(command, taskList);
        }
    }

    /**
     * Code entry point.
     *
     * @param args Entry point argument.
     */
    public static void main(String[] args) throws IndexOutOfBoundsException {
        new Sakura("./data/Sakura.txt").run();
    }
}
