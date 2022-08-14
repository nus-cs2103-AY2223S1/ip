import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "\t____________________________________________________________";

    private static void run() {
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
                String taskNumber;
                String[] splitStringArray;
                Task t;
                switch (command) {
                    case "bye":
                        isExited = true;
                        System.out.println(LINE
                                + "\n\tBye. Hope to see you again soon!\n"
                                + LINE);
                        break;
                    case "list":
                        if (tasks.size() == 0) {
                            throw new DukeException("You currently have no tasks in your list.");
                        } else {
                            System.out.println(LINE);
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.printf("\t%d.%s\n", i + 1, tasks.get(i));
                            }
                            System.out.println(LINE);
                        }
                        break;
                    case "mark":
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
                    case "unmark":
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
                    case "todo":
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
                                    + " tasks in the list.\n"
                                    + LINE);
                        }
                        break;
                    case "deadline":
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
                                    + " tasks in the list.\n"
                                    + LINE);
                        }
                        break;
                    case "event":
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
                                    + " tasks in the list.\n"
                                    + LINE);
                        }
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (NumberFormatException e) {
                System.out.printf("%s\n\t%s\n%s\n", LINE, "Please enter a valid task number!", LINE);
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
