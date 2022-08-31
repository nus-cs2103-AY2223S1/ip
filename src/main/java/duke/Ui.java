package duke;

import java.util.List;

/**
 * A class that handles the displaying of information to the user.
 */
public class Ui {
    private static final String LOGO =
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String BOT_NAME = "duke";
    private static final int LINE_LENGTH = 80;

    /**
     * Displays the text to be shown on starting up Duke.
     */
    public static void displayStartUpText() {
        System.out.println("Hello from\n" + LOGO);

        generateLine();
        displayTasks();
        generateLine();

        displayGreeting();
    }

    /**
     * Displays the text to be shown when the user greets Duke with hello.
     * This is also used in displayStartUpText.
     */
    public static void displayGreeting() {
        System.out.printf("Hello, I'm %s\n", BOT_NAME);
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a line of length LINE_LENGTH.
     */
    public static void generateLine() {
        System.out.println(String.format("%" + LINE_LENGTH + "s", "").replace(" ", "-"));
    }

    /**
     * Displays the text to be shown when the user exits the program.
     */
    public static void displayExitText() {
        System.out.println("Goodbye.");
    }

    /**
     * Displays the text to be shown when the user adds a task to the list.
     *
     * @param task The task that was added to the list.
     */
    public static void displayAddTaskMessage(Task task) {
        System.out.printf("Task added: %s\n", task);
        System.out.printf("You now have %d task(s) in your list\n", TaskList.getTaskCount());
    }

    /**
     * Displays the text to be shown when the user removes a task to the list.
     *
     * @param task The task that was removed from the list.
     */
    public static void displayDeleteTaskMessage(Task task) {
        System.out.printf("Task deleted: %s\n", task);
        System.out.printf("You now have %d task(s) in your list\n", TaskList.getTaskCount());
    }

    /**
     * Displays the text to be shown when the user marks a task on the list.
     *
     * @param task The task that was marked on the list.
     */
    public static void displayMarkTaskMessage(Task task, boolean isDone) {
        System.out.printf("Task marked as %s: %s\n", isDone ? "complete" : "incomplete", task);
    }

    /**
     * Displays the text to be shown when the user searches for tasks.
     *
     * @param foundTasks The tasks that were found in the search.
     * @param searchString The string that was used to perform the search.
     */
    public static void displaySearchTasksMessage(List<Task> foundTasks, String searchString) {
        if (foundTasks.size() == 0) {
            System.out.printf("Search results for \"%s\": No tasks found\n", searchString);
        } else {
            System.out.printf("Search results for \"%s\": %d task(s) found\n", searchString, foundTasks.size());
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, foundTasks.get(i));
            }
        }
    }

    /**
     * Displays all tasks in the list.
     */
    public static void displayTasks() {
        List<String> tasksStrings = TaskList.getTasksStrings();
        for (int i = 0; i < tasksStrings.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasksStrings.get(i));
        }

        System.out.printf("You have %d task(s) in your list\n", TaskList.getTaskCount());
    }
}
