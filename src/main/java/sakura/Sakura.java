package sakura;

import java.util.Scanner;

/**
 * Represents the Sakura chatbot object.
 */
public class Sakura {
    static boolean inProgress = true;
    public static TaskList taskList;
    public static Ui ui;
    public static Storage storage;
    public String filePath = "./data/Sakura.txt";

    /**
     * Constructor for Sakura.
     */
    public Sakura() {
        storage = new Storage(filePath);
        ui = new Ui();
        taskList = new TaskList(storage.loadData());
    }

    /**
     * Starts Sakura chatbot.
     */
    private void run() {
        System.out.println(Ui.greet());
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
        new Sakura().run();
    }

    public String getResponse(String input) {
        System.out.println(Ui.greet());
        return Parser.parseCommand(input, taskList);
    }
}
