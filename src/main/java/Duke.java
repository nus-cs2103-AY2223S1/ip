/**
 * @author Marciano Renzo William
 */

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
     * Private constructor of Duke.
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
