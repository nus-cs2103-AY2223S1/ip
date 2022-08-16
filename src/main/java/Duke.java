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
     * echos invalid commands.
     */
    private static void poll() {
        TaskItem task;
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
                        task = taskItems.get(Integer.parseInt(cmd) - 1);
                        task.isDone(true);
                        DukePrinter.mark(task.toString());
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        DukePrinter.markError(cmd);
                    }
                    break;
                case "unmark":
                    cmd = sc.next();
                    try {
                        task = taskItems.get(Integer.parseInt(cmd) - 1);
                        task.isDone(false);
                        DukePrinter.unmark(task.toString());
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        DukePrinter.unmarkError(cmd);
                    }
                    break;
                case "todo":
                    cmd = sc.nextLine();
                    try {
                        task = new TodoTask(cmd);
                        taskItems.add(task);
                        DukePrinter.add(task.toString(), taskItems.size());
                    } catch (IndexOutOfBoundsException e) {
                        DukePrinter.addError(cmd);
                    }
                    break;
                case "deadline":
                    cmd = sc.nextLine();
                    String[] deadlineTask = cmd.split("/by");
                    try {
                        task = new DeadlineTask(deadlineTask[0], deadlineTask[1]);
                        taskItems.add(task);
                        DukePrinter.add(task.toString(), taskItems.size());
                    } catch (IndexOutOfBoundsException e) {
                        DukePrinter.addError(cmd);
                    }
                    break;
                case "event":
                    cmd = sc.nextLine();
                    String[] eventTask = cmd.split("/at");
                    try {
                        task = new EventTask(eventTask[0], eventTask[1]);
                        taskItems.add(task);
                        DukePrinter.add(task.toString(), taskItems.size());
                    } catch (IndexOutOfBoundsException e) {
                        DukePrinter.addError(cmd);
                    }
                    break;
                default:
                    cmd = cmd + sc.nextLine();
                    DukePrinter.echo(cmd);
            }
            cmd = sc.next();
        }
    }
}
