package jenny;

import jenny.exceptions.TaskException;
import jenny.storage.TaskStorage;
import jenny.tasks.AbstractTask;
import jenny.tasks.DeadlineTask;
import jenny.tasks.EventTask;
import jenny.tasks.TodoTask;
import jenny.util.Printer;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Initialise the JennyBot application.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public final class JennyBot {
    private static final String MESSAGE_SCOPE = JennyBot.class.getSimpleName();
    private static final TaskStorage<ArrayList<AbstractTask>> TASK_STORAGE = new TaskStorage<>("tasks.txt");
    private static ArrayList<AbstractTask> tasks = new ArrayList<>();

    /**
     * Starting point of the application.
     *
     * @param args optional program arguments.
     */
    public static void main(String[] args) {
        Printer.greet();
        poll();
        Printer.exit();
    }

    /**
     * Continuously polls for valid commands.
     */
    private static void poll() {
        AbstractTask abstractTask;
        Scanner sc = new Scanner(System.in);
        String cmd = sc.next(); // get next command.

        while (!cmd.equals("bye")) {
            switch (cmd) {

            case "list":
                sc.nextLine(); // flush extra text after list command.
                if (tasks.isEmpty()) {
                    Printer.echo(MESSAGE_SCOPE, "There is nothing in your list to display.");
                } else {
                    Printer.list(new ArrayList<>(tasks));
                }
                break;

            case "mark":
                cmd = sc.nextLine().trim(); // get index number.
                try {
                    int index = Integer.parseInt(cmd) - 1; // -1 offset for zero indexed array.
                    abstractTask = tasks.get(index);
                    abstractTask.markAsDone(true);
                    Printer.mark(abstractTask.toString());
                } catch (NumberFormatException e) {
                    Printer.echo(MESSAGE_SCOPE, "You have entered a invalid number for mark <number>.");
                } catch (IndexOutOfBoundsException e) {
                    Printer.echo(MESSAGE_SCOPE, "No such record exists in your list.");
                }
                break;

            case "unmark":
                cmd = sc.nextLine().trim(); // get index number.
                try {
                    abstractTask = tasks.get(Integer.parseInt(cmd) - 1);
                    abstractTask.markAsDone(false);
                    Printer.unmark(abstractTask.toString());
                } catch (NumberFormatException e) {
                    Printer.echo(MESSAGE_SCOPE, "You have entered a invalid number for unmark <number>.");
                } catch (IndexOutOfBoundsException e) {
                    Printer.echo(MESSAGE_SCOPE, "No such record exists in your list.");
                }
                break;

            case "todo":
                cmd = sc.nextLine().trim(); // get abstractTask description.
                try {
                    abstractTask = new TodoTask(cmd);
                    tasks.add(abstractTask);
                    Printer.add(abstractTask.toString(), tasks.size());
                } catch (TaskException e) {
                    Printer.echo(e.getMessage());
                }
                break;

            case "deadline":
                cmd = sc.nextLine().trim(); // get abstractTask description and due date.
                String[] deadlineTask = cmd.split(" /by ");
                try {
                    abstractTask = new DeadlineTask(deadlineTask[0], deadlineTask[1]);
                    tasks.add(abstractTask);
                    Printer.add(abstractTask.toString(), tasks.size());
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.echo(MESSAGE_SCOPE,
                            "You have entered an invalid format for deadline <description> /by <due date>.");
                } catch (TaskException e) {
                    Printer.echo(e.getMessage());
                }
                break;

            case "event":
                cmd = sc.nextLine().trim(); // get abstractTask description and due date.
                String[] eventTask = cmd.split(" /at ");
                try {
                    abstractTask = new EventTask(eventTask[0], eventTask[1]);
                    tasks.add(abstractTask);
                    Printer.add(abstractTask.toString(), tasks.size());
                } catch (ArrayIndexOutOfBoundsException e) {
                    Printer.echo(MESSAGE_SCOPE,
                            "You have entered an invalid format for event <description> /by <due date>.");
                } catch (TaskException e) {
                    Printer.echo(e.getMessage());
                }
                break;

            case "delete":
                cmd = sc.nextLine().trim(); // get index number.
                try {
                    abstractTask = tasks.remove(Integer.parseInt(cmd) - 1);
                    Printer.delete(abstractTask.toString(), tasks.size());
                } catch (NumberFormatException e) {
                    Printer.echo(MESSAGE_SCOPE, "You have entered a invalid number for delete <number>.");
                } catch (IndexOutOfBoundsException e) {
                    Printer.echo(MESSAGE_SCOPE, "No such record exists in your list.");
                }
                break;

            case "save":
                TASK_STORAGE.save(tasks);
                break;

            case "load":
                tasks = TASK_STORAGE.load();
                Printer.list(new ArrayList<>(tasks));
                break;

            default:
                sc.nextLine(); // flush text after invalid command.
                Printer.echo(MESSAGE_SCOPE, "I'm sorry, but I don't know what that means.");
            }

            cmd = sc.next(); // get next command.
        }
    }
}
