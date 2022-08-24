import Exceptions.DeadlineJennyTaskException;
import Exceptions.EventJennyTaskException;
import Exceptions.TodoJennyTaskException;
import JennyTasks.*;
import JennyTasks.DeadlineJennyTask;
import JennyTasks.EventJennyTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Starting point of JennyBot chatbot.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */

public class JennyBot {
    private static ArrayList<JennyTask> jennyTasks = new ArrayList<>();

    /**
     * Starting point of the program.
     * @param args program arguments.
     */
    public static void main(String[] args) {
        JennyPrinter.greet();
        poll();
        JennyPrinter.exit();
    }

    /**
     * Continuously polls for valid commands.
     */
    private static void poll() {
        JennyTask jennyTask;
        Scanner sc = new Scanner(System.in);
        String cmd = sc.next(); // get next command.
        while(!cmd.equals("bye")) {
            switch (cmd) {
                case "list":
                    sc.nextLine(); // flush extra text after list command.
                    if (jennyTasks.isEmpty()) {
                        JennyPrinter.echo("☹ OOPS!!! Your list is empty.");
                    } else {
                        JennyPrinter.list(new ArrayList<>(jennyTasks));
                    }
                    break;
                case "mark":
                    cmd = sc.nextLine().trim(); // get index number.
                    try {
                        jennyTask = jennyTasks.get(Integer.parseInt(cmd) - 1);
                        jennyTask.isDone(true);
                        JennyPrinter.mark(jennyTask.toString());
                    } catch (NumberFormatException e) {
                        JennyPrinter.echo("☹ OOPS!!! You did not enter a number.");
                    } catch (IndexOutOfBoundsException e) {
                        JennyPrinter.echo("☹ OOPS!!! No such record exists.");
                    }
                    break;
                case "unmark":
                    cmd = sc.nextLine().trim(); // get index number.
                    try {
                        jennyTask = jennyTasks.get(Integer.parseInt(cmd) - 1);
                        jennyTask.isDone(false);
                        JennyPrinter.unmark(jennyTask.toString());
                    } catch (NumberFormatException e) {
                        JennyPrinter.echo("☹ OOPS!!! You did not enter a number.");
                    } catch (IndexOutOfBoundsException e) {
                        JennyPrinter.echo("☹ OOPS!!! No such record exists.");
                    }
                    break;
                case "todo":
                    cmd = sc.nextLine().trim(); // get jennyTask description.
                    try {
                        jennyTask = new TodoJennyTask(cmd);
                        jennyTasks.add(jennyTask);
                        JennyPrinter.add(jennyTask.toString(), jennyTasks.size());
                    } catch (TodoJennyTaskException e) {
                        JennyPrinter.echo(e.getMessage());
                    }
                    break;
                case "deadline":
                    cmd = sc.nextLine().trim(); // get jennyTask description and due date.
                    String[] deadlineTask = cmd.split(" /by ");
                    try {
                        jennyTask = new DeadlineJennyTask(deadlineTask[0], deadlineTask[1]);
                        jennyTasks.add(jennyTask);
                        JennyPrinter.add(jennyTask.toString(), jennyTasks.size());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        JennyPrinter.echo("☹ OOPS!!! Did you forget the description or due date?");
                    } catch (DeadlineJennyTaskException e) {
                        JennyPrinter.echo(e.getMessage());
                    }
                    break;
                case "event":
                    cmd = sc.nextLine().trim(); // get jennyTask description and due date.
                    String[] eventTask = cmd.split(" /at ");
                    try {
                        jennyTask = new EventJennyTask(eventTask[0], eventTask[1]);
                        jennyTasks.add(jennyTask);
                        JennyPrinter.add(jennyTask.toString(), jennyTasks.size());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        JennyPrinter.echo("☹ OOPS!!! Did you forget the description or due date?");
                    } catch (EventJennyTaskException e) {
                        JennyPrinter.echo(e.getMessage());
                    }
                    break;
                case "delete":
                    cmd = sc.nextLine().trim(); // get index number.
                    try {
                        jennyTask = jennyTasks.remove(Integer.parseInt(cmd) - 1);
                        JennyPrinter.delete(jennyTask.toString(), jennyTasks.size());
                    } catch (NumberFormatException e) {
                        JennyPrinter.echo("☹ OOPS!!! You did not enter a number.");
                    } catch (IndexOutOfBoundsException e) {
                        JennyPrinter.echo("☹ OOPS!!! No such record exists.");
                    }
                    break;
                case "save":
                    try {
                        JennyStorage jennyStorage = new JennyStorage();
                        jennyStorage.save(jennyTasks);
                    } catch (IOException e) {
                        JennyPrinter.echo("☹ OOPS!!! I/O Exception in JennyStorage.");
                    }
                    break;
                case "load":
                    try {
                        JennyStorage jennyStorage = new JennyStorage();
                        jennyTasks = jennyStorage.load();
                    } catch (IOException e) {
                        JennyPrinter.echo("☹ OOPS!!! I/O Exception in JennyStorage.");
                    } catch (ClassNotFoundException e) {
                        JennyPrinter.echo("☹ OOPS!!! Corrupted save file in JennyStorage.");
                    }
                    break;
                default:
                    sc.nextLine(); // flush text after invalid command.
                    JennyPrinter.echo("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            cmd = sc.next(); // get next command.
        }
    }
}
