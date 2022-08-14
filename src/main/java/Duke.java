import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    protected static final String LOGO = "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";
    protected static final String LINE = "\t____________________________________________________________";
    protected enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    protected static void run() {
        ArrayList<Task> tasks = new ArrayList<>();
        boolean isExited = false;
        String command = "";
        Scanner sc = new Scanner(System.in);

        System.out.println(LOGO
                + LINE
                + "\n"
                + "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + LINE);

        while (!isExited) {
            try {
                command = sc.next();
                Command c = Command.valueOf(command.toUpperCase());
                String taskNumber;
                String[] splitStringArray;
                Task t;
                switch (c) {
                    case BYE:
                        isExited = true;
                        System.out.println(LINE
                                + "\n\tBye. Hope to see you again soon!\n"
                                + LINE);
                        break;
                    case LIST:
                        if (tasks.isEmpty()) {
                            throw new DukeException("You currently have no tasks in your list.");
                        } else {
                            System.out.println(LINE);
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.printf("\t%d.%s\n", i + 1, tasks.get(i));
                            }
                            System.out.println(LINE);
                        }
                        break;
                    case MARK:
                        taskNumber = sc.nextLine();
                        if (taskNumber.isEmpty()) {
                            throw new DukeException("Please enter the task number to mark!");
                        } else {
                            t = tasks.get(Integer.parseInt(taskNumber.trim()) - 1);
                            t.markAsDone();
                            System.out.println(LINE
                                    + "\n\t"
                                    + "Nice! I've marked this task as done:\n\t  "
                                    + t
                                    + "\n"
                                    + LINE);
                        }
                        break;
                    case UNMARK:
                        taskNumber = sc.nextLine();
                        if (taskNumber.isEmpty()) {
                            throw new DukeException("Please enter the task number to unmark!");
                        } else {
                            t = tasks.get(Integer.parseInt(taskNumber.trim()) - 1);
                            t.markAsUndone();
                            System.out.println(LINE
                                    + "\n\t"
                                    + "OK, I've marked this task as not done yet:\n\t  "
                                    + t
                                    + "\n"
                                    + LINE);
                        }
                        break;
                    case TODO:
                        command = sc.nextLine();
                        if (command.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            ToDo td = new ToDo(command);
                            tasks.add(td);
                            System.out.println(LINE
                                    + "\n\t"
                                    + "Got it. I've added this task:\n\t  "
                                    + td
                                    + "\n\tNow you have "
                                    + tasks.size()
                                    + " task(s) in the list.\n"
                                    + LINE);
                        }
                        break;
                    case DEADLINE:
                        command = sc.nextLine();
                        if (command.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        } else {
                            splitStringArray = command.split(" /by ");
                            if (splitStringArray.length == 1) {
                                throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.");
                            }
                            Deadline dl = new Deadline(splitStringArray[0], splitStringArray[1]);
                            tasks.add(dl);
                            System.out.println(LINE
                                    + "\n\t"
                                    + "Got it. I've added this task:\n\t  "
                                    + dl
                                    + "\n\tNow you have "
                                    + tasks.size()
                                    + " task(s) in the list.\n"
                                    + LINE);
                        }
                        break;
                    case EVENT:
                        command = sc.nextLine();
                        if (command.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        } else {
                            splitStringArray = command.split(" /at ");
                            if (splitStringArray.length == 1) {
                                throw new DukeException("☹ OOPS!!! The date of an event cannot be empty.");
                            }
                            Event e = new Event(splitStringArray[0], splitStringArray[1]);
                            tasks.add(e);
                            System.out.println(LINE
                                    + "\n\t"
                                    + "Got it. I've added this task:\n\t  "
                                    + e
                                    + "\n\tNow you have "
                                    + tasks.size()
                                    + " task(s) in the list.\n"
                                    + LINE);
                        }
                        break;
                    case DELETE:
                        taskNumber = sc.nextLine();
                        if (tasks.isEmpty()) {
                            throw new DukeException("You currently have no tasks in your list to delete.");
                        } else {
                            if (taskNumber.isEmpty()) {
                                throw new DukeException("Please enter the task number to delete!");
                            } else {
                                t = tasks.remove(Integer.parseInt(taskNumber.trim()) - 1);
                                System.out.println(LINE
                                        + "\n\t"
                                        + "Noted. I've removed this task:\n\t  "
                                        + t
                                        + "\n\tNow you have "
                                        + tasks.size()
                                        + " task(s) in the list.\n"
                                        + LINE);
                            }
                        }
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.printf("%s\n\t%s\n%s\n", LINE, "Please enter a valid task number!", LINE);
            } catch (IllegalArgumentException e) {
                System.out.printf("%s\n\t%s\n%s\n", LINE
                        , "☹ OOPS!!! I'm sorry, but I don't know what that means :-(", LINE);
                sc.nextLine();
                continue;
            } catch (IndexOutOfBoundsException e) {
                System.out.printf("%s\n\t%s\n%s\n", LINE, "Task number does not exist!", LINE);
            } catch (DukeException e) {
                System.out.printf("%s\n\t%s\n%s\n", LINE, e.getMessage(), LINE);
            }
        }
    }

    public static void main(String[] args) {
        Duke.run();
    }
}
