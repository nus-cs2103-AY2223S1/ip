/**
 * @author Marciano Renzo William
 */

import java.util.Scanner;

/**
 * This is the Main Class that contains the Main method.
 */
public class Duke {
    private final UI Ui;
    private final TaskList Tasklist;

    /**
     * Private constructor of Duke.
     */
    private Duke() {
        Ui = new UI();
        Tasklist = new TaskList();
    }

    public void run() {
        Ui.printGreetings();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String chat = sc.nextLine();
            if (chat.equals("bye")) {
                sc.close();
                Ui.exit();
                break;
            } else if (chat.equals("list")) {
                Ui.showListDetails(Tasklist);
            } else {
                Tasklist.add(chat);
                Ui.showAddOnTask(chat);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
