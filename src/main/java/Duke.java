import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    enum Ability {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DELETE
    }

    public static void main(String[] args) {

        String hello = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.println(hello);

        Task[] tasks = new Task[100];

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] inputArr = input.split(" ");
        String action = inputArr[0];

        int size = 0;

        while (!action.equals("bye")) {
            int number;
            Task task;
            String[] params;
            try {
                switch (action) {
                    case "list":
                        if (size == 0) {
                            System.out.println("    ____________________________________________________________\n" +
                                    "     There is no pending task for you." +
                                    "\n    ____________________________________________________________");
                        } else {
                            System.out.println("    ____________________________________________________________\n" +
                                    "     Here are the tasks in your list:");
                            for (int i = 0; i < size; i++) {
                                System.out.format("     %d.%s\n", i + 1, tasks[i]);
                            }
                            System.out.println("    ____________________________________________________________");
                        }
                        break;
                    case "mark":
                        if (inputArr.length > 2 || inputArr.length == 1) {
                            throw new DukeException("The format should be: mark <number>!");
                        }
                        number = Integer.parseInt(inputArr[1]);
                        if (number > size) {
                            throw new DukeException("The index is invalid!");
                        }
                        task = tasks[number - 1];
                        task.markAsDone();
                        System.out.println("    ____________________________________________________________\n" +
                                "     Nice! I've marked this task as done:\n       " +
                                task +
                                "\n    ____________________________________________________________");
                        break;
                    case "unmark":
                        if (inputArr.length > 2 || inputArr.length == 1) {
                            throw new DukeException("The format should be: mark <number>!");
                        }
                        number = Integer.parseInt(inputArr[1]);
                        if (number > size) {
                            throw new DukeException("The index is invalid!");
                        }
                        task = tasks[number - 1];
                        task.markAsNotDone();
                        System.out.println("    ____________________________________________________________\n" +
                                "     Nice! I've marked this task as not done yet:\n       " +
                                task +
                                "\n    ____________________________________________________________");
                        break;
                    case "todo":
                        if (input.substring(4).replaceAll("\\s+", "").equals("")) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        task = new Todo(input.substring(5));
                        tasks[size] = task;
                        size++;
                        System.out.println("    ____________________________________________________________\n" +
                                "     Got it. I've added this task:\n       " +
                                task);
                        System.out.format("     Now you have %d tasks in the list.\n    ____________________________________________________________\n",
                                size);

                        break;
                    case "deadline":
                        if (input.substring(8).replaceAll("\\s+", "").equals("")) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                        if (!input.contains("/by")) {
                            throw new DukeException("The timing of a deadline cannot be omitted.");
                        }
                        params = input.substring(9).split(" /by ");
                        task = new Deadline(params[0], params[1]);
                        tasks[size] = task;
                        size++;
                        System.out.println("    ____________________________________________________________\n" +
                                "     Got it. I've added this task:\n       " +
                                task);
                        System.out.format("     Now you have %d tasks in the list.\n    ____________________________________________________________\n",
                                size);

                        break;
                    case "event":
                        if (input.substring(5).replaceAll("\\s+", "").equals("")) {
                            throw new DukeException("The description of an event cannot be empty.");
                        }
                        if (!input.contains("/at")) {
                            throw new DukeException("The timing of an event cannot be omitted.");
                        }
                        params = input.substring(6).split(" /at ");
                        task = new Event(params[0], params[1]);
                        tasks[size] = task;
                        size++;
                        System.out.println("    ____________________________________________________________\n" +
                                "     Got it. I've added this task:\n       " +
                                task);
                        System.out.format("     Now you have %d tasks in the list.\n    ____________________________________________________________\n",
                                size);
                        break;
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException err) {
                System.out.println("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! " +
                        err +
                        "\n    ____________________________________________________________\n");
            }
            input = sc.nextLine();
            inputArr = input.split(" ");
            action = inputArr[0];
        }

        // if("delete")

    }
}
