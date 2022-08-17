import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "\t____________________________________________________________";
    private static TaskList tasks = new TaskList();

    /**
     * Formats Duke's messages by adding horizontal line dividers and indentation.
     *
     * @param str Duke's message to be printed out
     */
    private static void prettyPrint(String str) {
        String[] messages = str.split("\n");
        System.out.println(DIVIDER);
        for (String message : messages) {
            System.out.println("\t  " + message);
        }
        System.out.println(DIVIDER);
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = String.join("\n", logo,
                "Hello! I'm Duke", "What can I do for you?");
        prettyPrint(welcomeMessage);
    }

    private static void goodbye() {
        String farewellMessage = "Bye. Hope to see you again soon!";
        prettyPrint(farewellMessage);
    }

    /**
     * Stores the specified task (to-do, event, deadline) into the linked list,
     * provided the respective task formats are properly followed.
     *
     * @param task The task to be recorded
     */
    private static void addTask(String task, TaskType type) throws DukeException {
        Task addedTask;
        switch (type) {
            case TODO:
                addedTask = TaskType.TODO.validateCommand(task);
                break;
            case EVENT:
                addedTask = TaskType.EVENT.validateCommand(task);
                break;
            default: // deadline
                addedTask = TaskType.DEADLINE.validateCommand(task);
        }
        prettyPrint(tasks.addTask(addedTask));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cmd;
        greet();
        // Echoes user's input until the user types 'bye', for which the program exits
        while (!(cmd = sc.nextLine()).equals("bye")) {
            try {
                switch (cmd) {
                    case "list":
                        prettyPrint(tasks.listTasks());
                        continue;
                    default:
                        if (cmd.matches("mark \\d+")) {
                           prettyPrint(tasks.markTaskDone(Integer.parseInt(cmd.substring(5))));
                        } else if (cmd.matches("unmark \\d+")) {
                            prettyPrint(tasks.markTaskNotDone(Integer.parseInt(cmd.substring(7))));
                        } else if (cmd.matches("delete \\d+")) {
                            prettyPrint(tasks.deleteTask(Integer.parseInt(cmd.substring(7))));
                        } else if (cmd.matches("(?i)^(todo)(.*)")) {
                            addTask(cmd, TaskType.TODO);
                        } else if (cmd.matches("(?i)^(deadline)(.*)")) {
                            addTask(cmd, TaskType.DEADLINE);
                        } else if (cmd.matches("(?i)^(event)(.*)")) {
                            addTask(cmd, TaskType.EVENT);
                        } else {
                            throw new DukeException("Hm...Duke doesn't understand what that means :(");
                        }
                }
            } catch (DukeException e) {
                prettyPrint(e.getMessage());
            }
        }
        goodbye();
    }
}
