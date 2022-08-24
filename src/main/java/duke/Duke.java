package duke;

import duke.processor.Parser;
import duke.processor.Storage;
import duke.processor.TaskList;
import duke.task.Task;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Marciano Renzo William
 */

/**
 * This is the Main Class that contains the Main method.
 */
public class Duke {
    private Ui ui;
    private TaskList tasklist;
    private Storage storage;

    /**
     * Private constructor of Duke.Duke.
     */
    private Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasklist = new TaskList(storage.read());
        } catch (IOException e) {
            tasklist = new TaskList();
        }
    }

    /**
     * Method to run the program.
     */
    public void run() {
        ui.printGreetings();
        boolean isStillRunning = true;
        Scanner sc = new Scanner(System.in);
        while (isStillRunning) {
            String chat = sc.nextLine();
            Task incomingTask = Parser.parse(chat, tasklist);
            incomingTask.execute(tasklist, ui, storage);
            isStillRunning = !incomingTask.isBye;
        }
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
