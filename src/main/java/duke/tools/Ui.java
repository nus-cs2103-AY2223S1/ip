package duke.tools;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.EnumMap;

import duke.exception.ContentNotFoundException;
import duke.exception.DateNotFoundException;
import duke.exception.TaskNotFoundException;
import duke.task.Task;

/**
 * Interaction with the user.
 */
public class Ui {
    private static final String GREETING = "Hello! I'm Duke, the reality check you never asked for but really need.\n"
            + "What can I help with today? \n"
            + "(psst, please input dates in the format: \n"
            + " yyyy-mm-dd)\n";

    private static final EnumMap<TaskParser.Tasks, String> taskResponses = new EnumMap<>(TaskParser.Tasks.class);

    static {
        taskResponses.put(TaskParser.Tasks.TODO, "Ah, more reality stuff. Here, I've added:\n");
        taskResponses.put(TaskParser.Tasks.DEADLINE, "Ah, more reality stuff. Here, I've added:\n");
        taskResponses.put(TaskParser.Tasks.EVENT, "Ah, more reality stuff. Here, I've added:\n");
    }

    /**
     * Greets the user when Duke is first opened.
     * @return A string containing the greeting.
     */
    public static String greet() {
        System.out.println(GREETING);
        return GREETING;
    }

    /**
     * Bids the user farewell when they close Duke.
     * @return A string containing a farewell message.
     */
    public static String goodbye() {
        System.out.println("Goodbye, see you soon for your next healthy reality check!");
        return "Goodbye, see you soon for your next healthy reality check!";
    }

    /**
     * Prints error message for TaskNotFoundException.
     * @param e TaskNotFoundException that was thrown.
     * @return A string for NoTaskException toast.
     */
    public static String noTaskExceptionToast(TaskNotFoundException e) {
        System.err.println(e.getMessage());
        System.out.println("Oops, I'm not sentient enough to understand that...");
        return "Oops, I'm not sentient enough to understand that...";
    }

    /**
     * Prints error message for ContentNotFoundException.
     * @param e ContentNotFoundException that was thrown.
     * @return A string for noContentException toast.
     */
    public static String noContentExceptionToast(ContentNotFoundException e) {
        System.err.println(e.getMessage());
        System.out.println("Doing nothing is great... but a task needs some content!");
        return "Doing nothing is great... but a task needs some content!";
    }

    /**
     * Prints error message for DateNotFoundException.
     * @param e DateNotFoundException that was thrown.
     * @return A string for noDateException toast.
     */
    public static String noDateExceptionToast(DateNotFoundException e) {
        System.err.println(e.getMessage());
        System.out.println("Events and Deadlines require a date!");
        return "Events and Deadlines require a date!";
    }

    /**
     * Prints exception message for DateTimeParseException.
     * @param e DateTimeParseException that was thrown.
     * @return A string for wrong date exception toast.
     */
    public static String wrongDateFormatToast(DateTimeParseException e) {
        System.err.println(e.toString());
        System.out.println("Dates should be of the form yyyy-mm-dd, not: " + e.getMessage());
        return "Dates should be of the form yyyy-mm-dd, not: " + e.getMessage();
    }

    /**
     * Prints acknowledgement message to screen.
     * @param TASK Constant representing the type of task.
     * @param task The task added.
     * @param size The size of the task list after the task is added.
     * @return String of task of added and the number of task in list.
     */
    public static String addTaskToast(TaskParser.Tasks TASK, Task task, int size) {
        assert task != null : "task is not null";
        System.out.println(taskResponses.get(TASK) + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        return taskResponses.get(TASK) + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Prints acknowledgement message after successfully loading saved task from file to task list.
     * @param task Task loaded to task list.
     * @param size Size of taskList after task is loaded.
     * @return A string containing toast when tasks are successfully loaded.
     */
    public static String loadTaskToast(Task task, int size) {
        assert task != null : "task is not null";
        System.out.println("This piece of past that you've ignored is becoming your reality:\n" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        return "This piece of past that you've ignored is becoming your reality:\n" + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Prints acknowledgement message after successfully deleting task from task list.
     * @param task Deleted task.
     * @param size Size of task list after deleting the task.
     * @return A string containing toast for successfully deleting a task.
     */
    public static String deleteTaskToast(Task task, int size) {
        assert task != null : "task is not null";
        System.out.println("This task has perished from reality:\n" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        return "This task has perished from reality:\n" + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Prints acknowledgement message after a task has been marked as completed.
     * @param task The task marked as completed.
     * @return A congratulatory message.
     */
    public static String congrats(Task task) {
        assert task != null : "task is not null";
        System.out.println("Congratulations on smashing reality!");
        System.out.println(task.toString());
        return "Congratulations on smashing reality!" + "\n"
                + task.toString();
    }

    /**
     * Prints acknowledgement message after a task has been marked as uncompleted.
     * @param task Task marked as uncompleted.
     * @return A string containing a toast for undone tasks.
     */
    public static String undoneToast(Task task) {
        assert task != null : "task is not null";
        System.out.println("Oops reality is catching up... this is still undone:");
        System.out.println(task.toString());
        return "Oops reality is catching up... this is still undone:\n"
                + task.toString();
    }

    /**
     * Prints list of tasks currently recorded.
     * @param tasks ArrayList of tasks.
     * @return A string containing a list of tasks.
     */
    public static String printList(ArrayList<Task> tasks) {
        if (tasks.size() <= 0) {
            System.out.println("There are no tasks in the list!");
            return "There are no tasks in the list!";
        }
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            int count = i + 1;
            output += (count + ". " + tasks.get(i).toString() + "\n");
        }
        System.out.println(output);
        return output;
    }

    /**
     * Returns task search result header.
     * @return Task search result header string.
     */
    public static String foundTaskToast() {
        System.out.println("Here are the tasks that match your search: ");
        return "Here are the tasks that match your search: ";
    }

    /**
     * Returns task sort result header.
     * @return Task sort result header string.
     */
    public static String sortTaskToast(String criteria) {
        System.out.println("Here are the tasks, sorted by " + criteria);
        return "Here are the tasks, sorted by " + criteria;
    }

}
