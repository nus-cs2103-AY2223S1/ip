package jenny;

import jenny.exceptions.TaskException;
import jenny.storage.Storage;
import jenny.tasks.AbstractTask;
import jenny.tasks.Deadline;
import jenny.tasks.Event;
import jenny.tasks.Todo;
import jenny.util.Printer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Starting point of jenny.JennyBot chatbot.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */

public final class JennyBot {
    private static ArrayList<AbstractTask> abstractTasks = new ArrayList<>();
    private static final String MESSAGE_SCOPE = JennyBot.class.getSimpleName();

    /**
     * Starting point of the program.
     *
     * @param args program arguments.
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
                if (abstractTasks.isEmpty()) {
                    Printer.echo(MESSAGE_SCOPE, "There is nothing in your list to display.");
                } else {
                    Printer.list(new ArrayList<>(abstractTasks));
                }
                break;

            case "mark":
                cmd = sc.nextLine().trim(); // get index number.
                try {
                    int index = Integer.parseInt(cmd) - 1; // -1 offset for zero indexed array.
                    abstractTask = abstractTasks.get(index);
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
                    abstractTask = abstractTasks.get(Integer.parseInt(cmd) - 1);
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
                    abstractTask = new Todo(cmd);
                    abstractTasks.add(abstractTask);
                    Printer.add(abstractTask.toString(), abstractTasks.size());
                } catch (TaskException e) {
                    Printer.echo(e.getMessage());
                }
                break;

            case "deadline":
                cmd = sc.nextLine().trim(); // get abstractTask description and due date.
                String[] deadlineTask = cmd.split(" /by ");
                try {
                    abstractTask = new Deadline(deadlineTask[0], deadlineTask[1]);
                    abstractTasks.add(abstractTask);
                    Printer.add(abstractTask.toString(), abstractTasks.size());
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
                    abstractTask = new Event(eventTask[0], eventTask[1]);
                    abstractTasks.add(abstractTask);
                    Printer.add(abstractTask.toString(), abstractTasks.size());
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
                    abstractTask = abstractTasks.remove(Integer.parseInt(cmd) - 1);
                    Printer.delete(abstractTask.toString(), abstractTasks.size());
                } catch (NumberFormatException e) {
                    Printer.echo(MESSAGE_SCOPE, "You have entered a invalid number for delete <number>.");
                } catch (IndexOutOfBoundsException e) {
                    Printer.echo(MESSAGE_SCOPE, "No such record exists in your list.");
                }
                break;

            case "save":
                try {
                    Storage storage = new Storage();
                    storage.save(abstractTasks);
                } catch (IOException e) {
                    Printer.echo(MESSAGE_SCOPE, "I/O Exception when saving in jenny.storage.Storage.");
                }
                break;

            case "load":
                try {
                    Storage storage = new Storage();
                    abstractTasks = storage.load();
                } catch (IOException e) {
                    Printer.echo(MESSAGE_SCOPE, "I/O Exception when loading in jenny.storage.Storage.");
                } catch (ClassNotFoundException e) {
                    Printer.echo(MESSAGE_SCOPE, "Loaded a corrupted save in jenny.storage.Storage.");
                }
                break;

            default:
                sc.nextLine(); // flush text after invalid command.
                Printer.echo(MESSAGE_SCOPE, "I'm sorry, but I don't know what that means.");
            }

            cmd = sc.next(); // get next command.
        }
    }
}
