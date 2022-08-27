package duke;

import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Main class for Duke
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     *Constructor for Duke class
     * Creates Ui and Storage instance
     * @param filePath File path for storage list
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program
     */
    public void run() {
        ui.sayHello();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        while(!isExit) {
            try {
                isExit = Parser.parse(sc.nextLine(), tasks, ui, storage);

            } catch (DukeException e) {
                System.out.println("Something went wrong " + e.getMessage());
            }
        }
    }

    /**
     * Main method of the Duke program
     * @param args Arguments passed to the main method
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
