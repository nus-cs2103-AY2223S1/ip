/**
 * @author Marciano Renzo William
 */

import java.util.Scanner;

/**
 * This is the Main Class that contains the Main method.
 */
public class Duke {
    private final UI ui;
    private final TaskList tasklist;

    /**
     * Private constructor of Duke.
     */
    private Duke() {
        ui = new UI();
        tasklist = new TaskList();
    }

    public void run() {
        ui.printGreetings();
        boolean isStillRunning = true;
        Scanner sc = new Scanner(System.in);
        while (isStillRunning) {
            String chat = sc.nextLine();
            Task incomingTask = Parser.parse(chat, tasklist);
            incomingTask.execute(tasklist, ui);
            isStillRunning = !incomingTask.isBye;
        }
        ui.exit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
