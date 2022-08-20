/**
 * Project done by Hong Jin.
 */
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Main class that runs chat bot Duke.
 */
public class Duke {

    public static final String initText = "Hello! I'm Duke\n    What can I do for you?";
    public static final String endText = "Bye bye! Hope to see you again soon!";

    private enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scan = new Scanner(System.in);
        printMsg(initText);
        TaskList ls = new TaskList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");

        while (true) {
            System.out.println("Say something: ");
            String input = scan.nextLine();
            String[] com = input.split(" ", 2);

            try {
                Command command = Command.valueOf(com[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new DukeException("No such command exist...");
            }

            Command command = Command.valueOf(com[0].toUpperCase());

            if (com.length == 1) {
                switch (command) {
                    case BYE:
                        printMsg(endText);
                        scan.close();
                        break;

                    case LIST:
                        printMsg(ls.enumerate());
                        break;

                    case MARK:
                        throw new DukeException("The index to mark cannot be left empty");

                    case UNMARK:
                        throw new DukeException("The index to unmark cannot be left empty");

                    case TODO:
                        throw new DukeException("The description of todo cannot be left empty");

                    case DEADLINE:
                        throw new DukeException("The description of deadline cannot be left empty");

                    case EVENT:
                        throw new DukeException("The description of event cannot be left empty");

                    case DELETE:
                        throw new DukeException("The index to delete cannot be left empty");

                    default:
                        throw new DukeException("No such command exist... please try again");
                }
            } else {
                switch (command) {
                    case BYE:
                        printMsg(endText);
                        scan.close();
                        break;

                    case LIST:
                        printMsg(ls.enumerate());
                        break;

                    case MARK:
                        try {
                            printMsg(ls.updateMark(Integer.parseInt(com[1])));
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("You don't have so many tasks phew...");
                        }
                        break;

                    case UNMARK:
                        try {
                            printMsg(ls.updateUnmark(Integer.parseInt(com[1])));
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("You don't have so many tasks phew...");
                        }
                        break;

                    case TODO:
                        printMsg(ls.addTask(new Task(com[1], "[T]")));
                        break;

                    case DEADLINE:
                        printMsg(ls.addTask(new Task(com[1], "[D]")));
                        break;

                    case EVENT:
                        printMsg(ls.addTask(new Task(com[1], "[E]")));
                        break;

                    case DELETE:
                        try {
                            printMsg(ls.deleteTask(Integer.parseInt(com[1])));
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("You don't have so many tasks phew...");
                        }
                        break;

                    default:
                        throw new DukeException("No such command exist... please try again");
                }
            }
        }
    }

    /**
     * Class method to print message with horizontal line.
     * @param str
     */
    public static void printMsg (String str){
        System.out.println("  ____________________________________________________________");
        System.out.println("    " + str);
        System.out.println("  ____________________________________________________________");
    }
}
