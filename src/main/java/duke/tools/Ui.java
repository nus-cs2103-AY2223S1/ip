package duke.tools;

import duke.exception.ContentNotFoundException;
import duke.exception.DateNotFoundException;
import duke.exception.TaskNotFoundException;
import duke.task.Task;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;
import java.util.EnumMap;

/**
 * Interaction with the user
 */
public class Ui {
    private static final String GREETING = "Hello! I'm Rio, the reality check you never asked for but really need.\n" +
            "What can I help with today?";
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

    public static void greet() {
        System.out.println(GREETING);
    }

    public static void goodbye() {
        System.out.println("Goodbye, see you soon for your next healthy reality check!");
    }

    public static void noTaskExceptionToast(TaskNotFoundException e) {
        System.err.println(e.getMessage());
        System.out.println("Oops, I'm not sentient enough to understand that...");
    }

    public static void noContentExceptionToast(ContentNotFoundException e) {
        System.err.println(e.getMessage());
        System.out.println("Doing nothing is great... but a task needs some content!");
    }

    public static void noDateExceptionToast(DateNotFoundException e) {
        System.err.println(e.getMessage());
        System.out.println("Events and Deadlines require a date!");
    }

    public static void wrongDateFormatToast(DateTimeParseException e) {
        System.err.println(e.toString());
        System.out.println("Dates should be of the form yyyy-mm-dd, not: " + e.getMessage());
    }

    public static void addTaskToast(TaskParser.TASKS TASK, Task task, int size) {
        System.out.println(taskResponses.get(TASK) + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void loadTaskToast(Task task, int size) {
        System.out.println("This piece of past that you've ignored is becoming your reality:\n" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void deleteTaskToast(Task task, int size) {
        System.out.println("This task has perished from reality:\n" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void congrats(Task task) {
        System.out.println("Congratulations on smashing reality!");
        System.out.println(task.toString());
    }

    public static void undoneToast(Task task) {
        System.out.println("Oops reality is catching up... this is still undone:");
        System.out.println(task.toString());
    }

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
