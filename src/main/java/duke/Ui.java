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
    private static final String BOT_NAME = "Duke";
    private static final int LINE_LENGTH = 35;

    /**
     * Displays the text to be shown on starting up Duke.
     */
    public static void displayStartUpText() {
        Duke.addToResponse("Hello from\n" + LOGO);

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
        Duke.addToResponse(String.format("Hello, I'm %s\n", BOT_NAME));
        Duke.addToResponse(String.format("What can I do for you?\n"));
    }

    /**
     * Displays a line of length LINE_LENGTH.
     */
    public static void generateLine() {
        Duke.addToResponse(String.format("%" + LINE_LENGTH + "s", "").replace(" ", "-") + "\n");
    }

    /**
     * Displays the text to be shown when the user exits the program.
     */
    public static void displayExitText() {
        Duke.addToResponse("Goodbye.\n");
    }

    /**
     * Displays the text to be shown when the user adds a task to the list.
     *
     * @param task The task that was added to the list.
     */
    public static void displayAddTaskMessage(Task task) {
        Duke.addToResponse(String.format("Task added:\n%s\n", task));
        Duke.addToResponse(String.format("You now have %d task(s) in your list\n", TaskList.getTaskCount()));
    }

    /**
     * Displays the text to be shown when the user removes a task to the list.
     *
     * @param task The task that was removed from the list.
     */
    public static void displayDeleteTaskMessage(Task task) {
        Duke.addToResponse(String.format("Task deleted:\n%s\n", task));
        Duke.addToResponse(String.format("You now have %d task(s) in your list\n", TaskList.getTaskCount()));
    }

    /**
     * Displays the text to be shown when the user marks a task on the list.
     *
     * @param task The task that was marked on the list.
     */
    public static void displayMarkTaskMessage(Task task, boolean isDone) {
        Duke.addToResponse(String.format("Task marked as %s:\n%s\n", isDone ? "complete" : "incomplete", task));
    }

    /**
     * Displays the text to be shown when the user searches for tasks.
     *
     * @param foundTasks The tasks that were found in the search.
     * @param searchString The string that was used to perform the search.
     */
    public static void displaySearchTasksMessage(List<Task> foundTasks, String searchString) {
        Duke.addToResponse(String.format("Displaying search results for \"%s\":\n",
                searchString, foundTasks.size()));

        for (int i = 0; i < foundTasks.size(); i++) {
            Duke.addToResponse(String.format("%d. %s\n", i + 1, foundTasks.get(i)));
        }

        Duke.addToResponse(String.format("\n%d task(s) found\n", foundTasks.size()));
    }

    /**
     * Displays all tasks in the list.
     */
    public static void displayTasks() {
        List<String> tasksStrings = TaskList.getTasksStrings();

        for (int i = 0; i < tasksStrings.size(); i++) {
            Duke.addToResponse(String.format("%d. %s\n", i + 1, tasksStrings.get(i)));
        }

        Duke.addToResponse(String.format("\nYou have %d task(s) in your list\n", TaskList.getTaskCount()));
    }

    /**
     * Displays the text to be shown when the user sorts the tasks by name
     */
    public static void displaySortTasksByNameMessage(String order) {
        Duke.addToResponse(String.format("Tasks sorted by name in %s order\n", order));
        displayTasks();
    }
}
