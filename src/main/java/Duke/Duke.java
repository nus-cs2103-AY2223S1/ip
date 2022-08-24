package Duke;

import Duke.Exception.NoCommandException;
import Duke.Processor.Parser;
import Duke.Processor.Storage;
import Duke.Processor.TaskList;
import Duke.Task.Task;

import java.io.IOException;
import java.util.Scanner;

/**
 * This is the Main Class that contains the Main method.
 */
public class Duke {
    private UI ui;
    private TaskList tasklist;
    private Storage storage;

    /**
     * Private constructor of Duke.Duke.
     *
     * @param filePath Path of the file to write/read.
     */
    private Duke(String filePath) {
        ui = new UI();
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
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
