import java.util.ArrayList;
import java.util.Scanner;

/**
 * Starting point of Duke chatbot.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */

public class Duke {
    private static final ArrayList<TaskItem> taskItems = new ArrayList<>(100);
    public static void main(String[] args) {
        DukePrinter.greet();
        poll();
        DukePrinter.exit();
    }

    /**
     * Continuously polls for valid commands,
     * adds invalid commands as tasks.
     */
    private static void poll() {
        Scanner sc = new Scanner(System.in);
        String cmd = sc.next();
        while(!cmd.equals("bye")) {
            switch (cmd) {
                case "list":
                    DukePrinter.list(new ArrayList<>(taskItems));
                    break;
                case "mark":
                    cmd = sc.next();
                    try {
                        TaskItem task = taskItems.get(Integer.parseInt(cmd) - 1);
                        task.isDone(true);
                        DukePrinter.mark(task.icon(), task.toString());
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        DukePrinter.markError(cmd);
                    }
                    break;
                case "unmark":
                    cmd = sc.next();
                    try {
                        TaskItem task = taskItems.get(Integer.parseInt(cmd) - 1);
                        task.isDone(false);
                        DukePrinter.unmark(task.icon(), task.toString());
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        DukePrinter.unmarkError(cmd);
                    }
                    break;
                default:
                    cmd = cmd + sc.nextLine();
                    try {
                        taskItems.add(new TaskItem(cmd));
                        DukePrinter.add(cmd);
                    } catch (IndexOutOfBoundsException e) {
                        DukePrinter.addError(cmd);
                    }
            }
            cmd = sc.next();
        }
    }
}
