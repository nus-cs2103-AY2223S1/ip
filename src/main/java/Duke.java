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

        Scanner sc = new Scanner(System.in);
        while (true) {
            String chat = sc.nextLine();
            if (chat.equals("bye")) {
                sc.close();
                ui.exit();
                break;
            } else if (chat.equals("list")) {
                ui.showListDetails(tasklist);
            } else if (chat.contains("unmark")) {
                int num = Integer.parseInt(chat.split(" ")[1]) - 1;
                tasklist.markUndone(num);
                ui.showUndoneTask(tasklist, num);
            } else if (chat.contains("mark")) {
                int num = Integer.parseInt(chat.split(" ")[1]) - 1;
                tasklist.markDone(num);
                ui.showDoneTask(tasklist, num);
            } else {
                if (chat.contains("todo")) {
                    Task incomingTask = new Todo(chat.substring(5), false);
                    incomingTask.execute(tasklist, ui);
                } else if (chat.contains("deadline")) {
                    Task incomingTask = new Deadline(chat.substring(9).split(" /by ")[0], false,
                            chat.substring(9).split(" /by ")[1]);
                    incomingTask.execute(tasklist, ui);
                } else if (chat.contains("event")) {
                    Task incomingTask = new Event(chat.substring(6).split(" /at ")[0], false,
                            chat.substring(6).split(" /at ")[1]);
                    incomingTask.execute(tasklist, ui);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
