import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Main driver class of the Duke Application.
 */
public class Duke {
    /** Line used for formatting. */
    public static String line = "----------------------------------------";
    /** Use an ArrayList Collection for handling the tasks. */
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * The main method that is the entry to the Duke Application.
     * @param args Command line arguments that we can pass to the main function.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "");
        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);

        String current = sc.nextLine();
        while (!current.equals("bye")) {
            String[] splitString = current.split(" ");
            String command = splitString[0];
            try {
                switch (command) {
                case "list":
                    printTasks();
                    break;
                case "mark":
                    int taskIdToMark = splitString.length == 2
                            ? Integer.parseInt(splitString[1])
                            : -1;
                    changeTaskStatus(taskIdToMark, true);
                    break;
                case "unmark":
                    int taskIdToUnmark = splitString.length == 2
                            ? Integer.parseInt(splitString[1])
                            : -1;
                    changeTaskStatus(taskIdToUnmark, false);
                    break;
                case "delete":
                    int taskIdToDelete = splitString.length == 2
                            ? Integer.parseInt(splitString[1])
                            : -1;
                    deleteTask(taskIdToDelete);
                    break;
                case "todo":
                    String[] descTodo = Arrays.copyOfRange(splitString, 1, splitString.length);
                    current = String.join(" ", descTodo);
                    Task todoTask = new Todo(current);
                    addTask(todoTask);
                    break;
                case "deadline":
                    String[] descDeadline = Arrays.copyOfRange(splitString, 1, splitString.length);
                    current = String.join(" ", descDeadline);
                    String descD = current.split("/by")[0].trim();
                    String byD = current.split("/by").length == 2
                            ? current.split("/by")[1].trim()
                            : "";
                    Task deadlineTask = new Deadline(descD, byD);
                    addTask(deadlineTask);
                    break;
                case "event":
                    String[] descEvent = Arrays.copyOfRange(splitString, 1, splitString.length);
                    current = String.join(" ", descEvent);
                    String descE = current.split("/at")[0].trim();
                    String atE = current.split("/at").length == 2
                            ? current.split("/at")[1].trim()
                            : "";
                    Task eventTask = new Event(descE, atE);
                    addTask(eventTask);
                    break;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means!");
                }
            } catch (Exception e) {
                System.out.println(line);
                System.out.println("OOPS!!! " + e.getMessage());
                System.out.println(line);
            }

            current = sc.nextLine();
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        sc.close();
        System.exit(0);
    }

    /**
     * Function to add a task to our Task collection.
     * @param task The task that we would like to add to our tasks Collection.
     */
    public static void addTask(Task task) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        tasks.add(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Function to delete a task from our Task collection.
     * @param index The index of the task that we would like to remove from collection.
     * @throws DukeException For Duke project related exceptions.
     */
    public static void deleteTask(int index) throws DukeException {
        if (index == -1) {
            throw new DukeException("You must specify which task to delete!");
        }

        Task task = tasks.get(index - 1);
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        tasks.remove(index - 1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Function to print the tasks in our Task collection with nice formatting.
     */
    public static void printTasks() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task current = tasks.get(i);
            System.out.println((i + 1) + "." + current.toString());
        }
        System.out.println(line);
    }

    /**
     * Function to change the status of a task and to mark it as done or not done,
     * used for marking a task as done or not done.
     * @param taskId The id of the task that we want to mark or unmark.
     * @param isDone The new status of the task. We will update the task to have
     *               its isDone field to be the value here.
     * @throws DukeException For Duke related exceptions.
     */
    public static void changeTaskStatus(int taskId, boolean isDone) throws DukeException {
        if (taskId == -1) {
            throw new DukeException("You must specify which task to mark or unmark!");
        }

        Task task = tasks.get(taskId - 1);
        task.setDoneStatus(isDone);
        System.out.println(line);
        if (isDone) {
            System.out.println(("Nice! I've marked this task as done:"));
            System.out.println("  " + task.toString());
        } else {
            System.out.println(("OK, I've marked this task as not done yet:"));
            System.out.println("  " + task.toString());
        }
        System.out.println(line);
    }
}
