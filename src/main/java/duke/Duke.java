package duke;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.IOException;

/**
 * The class of the Duke bot
 */
public class Duke {

    /**
     * Storage object that handles loading tasks from the
     * file and saving tasks in the file
     */
    private final Storage storage;

    /**
     * TaskLists object that contains a task list containing Task objects
     */
    private TaskList tasks;

    /**
     * A constructor that initializes the Duke object
     * @param filePath the filepath of the Duke text file
     */
    public Duke(String filePath) {
        Ui ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that runs the Duke bot
     */
    public void run() {
        Ui.helloMessage();
        Scanner first = new Scanner(System.in);
        boolean canExit = false;
        while(!canExit) {
            canExit = Parser.parse(first.nextLine(), tasks, storage);
        }
    }

    public static void main(String[] args) {
        new Duke("./src/main/java/duke.txt").run();
    }
}