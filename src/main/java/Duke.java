import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "\t____________________________________________________________";
    private static LinkedList<Task> tasks = new LinkedList<>();

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
     * Lists all the tasks entered thus far by the user.
     * Will print 'No tasks' if no tasks are found.
     */
    private static void listTasks() {
        String taskList = "";
        int count = 0;
        for (Task task : tasks) {
            count++;
            taskList += String.format("\n%d. %s", count, task);
        }
        prettyPrint(count != 0 ? "Here are the tasks in your list:\n"
                + taskList.substring(1) : "No tasks");
    }

    /**
     * Stores the specified task (to-do, event, deadline) into the linked list,
     * provided the respective task formats are properly followed.
     *
     * @param task The task to be recorded
     */
    private static void addTask(String task) throws DukeException {
        Task addedTask;
        if (task.matches("(?i)(^todo)(.*)")) {
            // adding a to-do task, check that format is followed
            if (task.matches("(?i)^todo\\s.+")) {
                addedTask = new ToDo(task.substring(5));
            } else {
                throw new DukeException("Hm... Duke's confused. Are you trying to create a todo?" +
                        "\nMake sure you follow the format: todo [description].\n" +
                        "The description of a todo cannot be empty!");
            }
        } else if (task.matches("(?i)(^deadline)(.*)")) {
            // adding a deadline, check that format is followed
            if (task.matches("(?i)^deadline\\s.+\\s\\/(by)\\s.+")) {
                String[] sp = task.substring(9).split("\\/(by)\\s", 2);
                addedTask = new Deadline(sp[0], sp[1]);
            } else {
                throw new DukeException("Hm... Duke's confused. Are you trying to create a deadline?" +
                        "\nMake sure you follow the format: deadline [description] /by [deadline]");
            }
        } else {
            // adding an event, check that format is followed
            if (task.matches("(?i)^event\\s.+\\s\\/(at)\\s.+")) {
                String[] sp = task.substring(6).split("\\/(at)\\s", 2);
                addedTask = new Event(sp[0], sp[1]);
            } else {
                throw new DukeException("Hm... Duke's confused. Are you trying to create an event?" +
                        "\nMake sure you follow the format: event [description] /at [event datetime]");
            }
        }

        tasks.add(addedTask);
        prettyPrint(String.format("Got it. I've added this task:\n" +
                "  %s\nNow you have %d tasks in the list.", addedTask, tasks.size()));
    }

    /**
     * Marks the specified task number as done, if it exists.
     *
     * @param i The task number to be marked as done
     */
    private static void markTaskDone(int i) throws DukeException {
        if (isValidTask(i)) {
            Task task = tasks.get(i - 1);
            task.markTaskAsDone();
            prettyPrint(String.format("Nice! I've marked this task as done:\n %s", task));
        }
    }

    /**
     * Marks the specified task number as not done, if it exists.
     *
     * @param i The task number to be marked as not done
     */
    private static void markTaskNotDone(int i) throws DukeException {
        if (isValidTask(i)) {
            Task task = tasks.get(i - 1);
            task.markTaskAsUndone();
            prettyPrint(String.format("OK, I've marked this task as not done yet:\n %s", task));
        }
    }

    /**
     * Checks that the specified task is a task that exists.
     *
     * @param i The task number of the task to be verified
     * @return True if the task exists, false otherwise
     */
    private static boolean isValidTask(int i) throws DukeException {
        boolean isValid = i <= tasks.size();
        if (!isValid) throw new DukeException("Hm... Duke can't find this task.");
        return true;
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
                        listTasks();
                        continue;
                    default:
                        if (cmd.matches("mark \\d+")) {
                            markTaskDone(Integer.parseInt(cmd.substring(5)));
                        } else if (cmd.matches("unmark \\d+")) {
                            markTaskNotDone(Integer.parseInt(cmd.substring(7)));
                        } else if (cmd.matches("(?i)^(todo|deadline|event)(.*)")) {
                            addTask(cmd);
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
