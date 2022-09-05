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
                    number = Integer.parseInt(inputArr[1]);
                    task = tasks[number - 1];
                    task.markAsDone();
                    System.out.println("    ____________________________________________________________\n" +
                            "     Nice! I've marked this task as done:\n       " +
                            task +
                            "\n    ____________________________________________________________");
                    break;
                case "unmark":
                    number = Integer.parseInt(inputArr[1]);
                    task = tasks[number - 1];
                    task.markAsNotDone();
                    System.out.println("    ____________________________________________________________\n" +
                            "     Nice! I've marked this task as not done yet:\n       " +
                            task +
                            "\n    ____________________________________________________________");
                    break;
                case "todo":
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
                    task = new Task(input);
                    tasks[size] = task;
                    size++;
                    System.out.println("    ____________________________________________________________\n" +
                            "     Got it. I've added this task:\n       " +
                            task);
                    System.out.format("     Now you have %d tasks in the list.\n    ____________________________________________________________\n",
                            size);
                    break;
            }
            input = sc.nextLine();
            inputArr = input.split(" ");
            action = inputArr[0];
        }

        // if("delete")

    }
}
