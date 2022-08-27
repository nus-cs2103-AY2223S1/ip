package duke;

import java.io.*;
import java.util.*;

/**
 * Represents the main Duke class.
 *
 * @author Denzel Tan
 */
public class Duke {
    private TaskList tasks;

    /**
     * Constructor for the Duke class.
     *
     * @param filePath path of the file to be used
     */
    public Duke(String filePath) {
        Storage storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }


    /**
     * Run method to run the Duke object.
     */
    public void run() {
        Ui.sayGreeting();
        Scanner sc = new Scanner(System.in);
        boolean canQuit = false;

        while (!canQuit) {
            canQuit = Parser.parse(sc.nextLine(), tasks);
        }
    }

    /**
     * The main function.
     */
    public static void main(String[] args) {
        new Duke("./src/main/java/duke.txt").run();
    }
}
