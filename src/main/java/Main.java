import duke.Duke;

import java.io.File;

/**
 * Entry point of the Duke application.
 * Initialises Duke and creates the directory to store the task list.
 *
 * The String path is the location of the stored task list that Duke will retrieve and write to.
 */
public class Main {
    public static void main(String[] args) {
        String path = "data/duke.txt";
        new File("data").mkdir();
        Duke duke = new Duke(path);
        duke.runDuke();
    }
}
