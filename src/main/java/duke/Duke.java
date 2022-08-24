package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.exception.NoCommandException;
import duke.processor.Parser;
import duke.processor.Storage;
import duke.processor.TaskList;
import duke.task.Task;

/**
 * This is the Main Class that contains the Main method.
 */
public class Duke {
    private Ui ui;
    private TaskList tasklist;
    private Storage storage;

    /**
     * Private constructor of Duke.Duke.
     *
     * @param filePath Path of the file to write/read.
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
     * Runs the main logic of the program.
     *
     * @throws NoCommandException If there are no commands.
     */
    public void run() throws NoCommandException {
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

    /**
     * Runs the run method.
     *
     * @param args
     */
    public static void main(String[] args) throws NoCommandException {
        new Duke("data/duke.txt").run();
    }
}
