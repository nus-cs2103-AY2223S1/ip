package duke.tools;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.EnumMap;

import duke.exception.ContentNotFoundException;
import duke.exception.DateNotFoundException;
import duke.exception.TaskNotFoundException;
import duke.task.Task;

/**
 * Interaction with the user
 */
public class Ui {
    private static final String GREETING = "Hello! I'm Duke, the reality check you never asked for but really need.\n"
            + "What can I help with today?";

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final EnumMap<TaskParser.TASKS, String> taskResponses = new EnumMap<>(TaskParser.TASKS.class);

    static {
        taskResponses.put(TaskParser.TASKS.TODO, "Ah, more reality stuff. Here, I've added:\n");
        taskResponses.put(TaskParser.TASKS.DEADLINE, "Ah, more reality stuff. Here, I've added:\n");
        taskResponses.put(TaskParser.TASKS.EVENT, "Ah, more reality stuff. Here, I've added:\n");
    }

    /**
     * Greets the user when Duke is first opened.
     */
    public static void greet() {
        System.out.println(GREETING);
    }

    /**
     * Bids the user farewell when they close Duke.
     */
    public static void goodbye() {
        System.out.println("Goodbye, see you soon for your next healthy reality check!");
    }

    /**
     * Prints error message for TaskNotFoundException.
     * @param e TaskNotFoundException that was thrown.
     */
    public static void noTaskExceptionToast(TaskNotFoundException e) {
        System.err.println(e.getMessage());
        System.out.println("Oops, I'm not sentient enough to understand that...");
    }

    /**
     * Prints error message for ContentNotFoundException.
     * @param e ContentNotFoundException that was thrown.
     */
    public static void noContentExceptionToast(ContentNotFoundException e) {
        System.err.println(e.getMessage());
        System.out.println("Doing nothing is great... but a task needs some content!");
    }

    /**
     * Prints error message for DateNotFoundException.
     * @param e DateNotFoundException that was thrown.
     */
    public static void noDateExceptionToast(DateNotFoundException e) {
        System.err.println(e.getMessage());
        System.out.println("Events and Deadlines require a date!");
    }

    /**
     * Prints exception message for DateTimeParseException.
     * @param e DateTimeParseException that was thrown.
     */
    public static void wrongDateFormatToast(DateTimeParseException e) {
        System.err.println(e.toString());
        System.out.println("Dates should be of the form yyyy-mm-dd, not: " + e.getMessage());
    }

    /**
     * Prints acknowledgement message to screen.
     * @param TASK Constant representing the type of task.
     * @param task The task added.
     * @param size The size of the task list after the task is added.
     */
    public static void addTaskToast(TaskParser.TASKS TASK, Task task, int size) {
        System.out.println(taskResponses.get(TASK) + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints acknowledgement message after successfully loading saved task from file to task list.
     * @param task Task loaded to task list.
     * @param size Size of taskList after task is loaded.
     */
    public static void loadTaskToast(Task task, int size) {
        System.out.println("This piece of past that you've ignored is becoming your reality:\n" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints acknowledgement message after successfully deleting task from task list.
     * @param task Deleted task.
     * @param size Size of task list after deleting the task.
     */
    public static void deleteTaskToast(Task task, int size) {
        System.out.println("This task has perished from reality:\n" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints acknowledgement message after a task has been marked as completed.
     * @param task The task marked as completed.
     */
    public static void congrats(Task task) {
        System.out.println("Congratulations on smashing reality!");
        System.out.println(task.toString());
    }

    /**
     * Prints acknowledgement message after a task has been marked as uncompleted.
     * @param task Task marked as uncompleted.
     */
    public static void undoneToast(Task task) {
        System.out.println("Oops reality is catching up... this is still undone:");
        System.out.println(task.toString());
    }

    /**
     * Prints list of tasks currently recorded.
     * @param tasks ArrayList of tasks.
     */
    public static void printList(ArrayList<Task> tasks) {
        if (tasks.size() <= 0) {
            System.out.println("There are no tasks in the list!");
        }
        for (int i = 0; i < tasks.size(); i++) {
            int count = i + 1;
            System.out.println(count + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Prints task search result header.
     */
    public static void foundTaskToast() {
        System.out.println("Here are the tasks that match your search: ");
    }

}
