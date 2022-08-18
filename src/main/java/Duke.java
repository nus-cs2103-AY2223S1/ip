import Exceptions.DeadlineTaskException;
import Exceptions.EventTaskException;
import Exceptions.TodoTaskException;
import TaskItems.DeadlineTask;
import TaskItems.EventTask;
import TaskItems.TaskItem;
import TaskItems.TodoTask;

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
    private static final ArrayList<TaskItem> taskItems = new ArrayList<>();

    /**
     * Starting point of the program.
     * @param args program arguments.
     */
    public static void main(String[] args) {
        DukePrinter.greet();
        poll();
        DukePrinter.exit();
    }

    /**
     * Continuously polls for valid commands.
     */
    private static void poll() {
        TaskItem task;
        Scanner sc = new Scanner(System.in);
        String cmd = sc.next(); // get next command.
        while(!cmd.equals("bye")) {
            switch (cmd) {
                case "list":
                    sc.nextLine(); // flush extra text after list command.
                    if (taskItems.isEmpty()) {
                        DukePrinter.echo("☹ OOPS!!! Your list is empty.");
                    } else {
                        DukePrinter.list(new ArrayList<>(taskItems));
                    }
                    break;
                case "mark":
                    cmd = sc.nextLine().trim(); // get index number.
                    try {
                        task = taskItems.get(Integer.parseInt(cmd) - 1);
                        task.isDone(true);
                        DukePrinter.mark(task.toString());
                    } catch (NumberFormatException e) {
                        DukePrinter.echo("☹ OOPS!!! You did not enter a number.");
                    } catch (IndexOutOfBoundsException e) {
                        DukePrinter.echo("☹ OOPS!!! No such record exists.");
                    }
                    break;
                case "unmark":
                    cmd = sc.nextLine().trim(); // get index number.
                    try {
                        task = taskItems.get(Integer.parseInt(cmd) - 1);
                        task.isDone(false);
                        DukePrinter.unmark(task.toString());
                    } catch (NumberFormatException e) {
                        DukePrinter.echo("☹ OOPS!!! You did not enter a number.");
                    } catch (IndexOutOfBoundsException e) {
                        DukePrinter.echo("☹ OOPS!!! No such record exists.");
                    }
                    break;
                case "todo":
                    cmd = sc.nextLine().trim(); // get task description.
                    try {
                        task = new TodoTask(cmd);
                        taskItems.add(task);
                        DukePrinter.add(task.toString(), taskItems.size());
                    } catch (TodoTaskException e) {
                        DukePrinter.echo(e.getMessage());
                    }
                    break;
                case "deadline":
                    cmd = sc.nextLine().trim(); // get task description and due date.
                    String[] deadlineTask = cmd.split("/by");
                    try {
                        task = new DeadlineTask(deadlineTask[0], deadlineTask[1]);
                        taskItems.add(task);
                        DukePrinter.add(task.toString(), taskItems.size());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        DukePrinter.echo("☹ OOPS!!! Did you forget the description or due date?");
                    } catch (DeadlineTaskException e) {
                        DukePrinter.echo(e.getMessage());
                    }
                    break;
                case "event":
                    cmd = sc.nextLine().trim(); // get task description and due date.
                    String[] eventTask = cmd.split("/at");
                    try {
                        task = new EventTask(eventTask[0], eventTask[1]);
                        taskItems.add(task);
                        DukePrinter.add(task.toString(), taskItems.size());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        DukePrinter.echo("☹ OOPS!!! Did you forget the description or due date?");
                    } catch (EventTaskException e) {
                        DukePrinter.echo(e.getMessage());
                    }
                    break;
                case "delete":
                    cmd = sc.nextLine().trim(); // get index number.
                    try {
                        task = taskItems.remove(Integer.parseInt(cmd) - 1);
                        DukePrinter.delete(task.toString(), taskItems.size());
                    } catch (NumberFormatException e) {
                        DukePrinter.echo("☹ OOPS!!! You did not enter a number.");
                    } catch (IndexOutOfBoundsException e) {
                        DukePrinter.echo("☹ OOPS!!! No such record exists.");
                    }
                    break;
                default:
                    sc.nextLine(); // flush text after invalid command.
                    DukePrinter.echo("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            cmd = sc.next(); // get next command.
        }
    }
}
