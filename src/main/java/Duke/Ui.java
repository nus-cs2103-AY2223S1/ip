package Duke;

import java.util.List;

/**
 * A class that handles displaying of information to the user.
 */
public class Ui {
    private static final String LOGO = " ____        _        \n"
                                     + "|  _ \\ _   _| | _____ \n"
                                     + "| | | | | | | |/ / _ \\\n"
                                     + "| |_| | |_| |   <  __/\n"
                                     + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String BOT_NAME = "Duke";
    private static final int LINE_LENGTH = 80;

    public static void displayStartUpText() {
        System.out.println("Hello from\n" + LOGO);
        displayGreeting();
    }

    public static void displayGreeting() {
        generateLine();
        displayTasks();
        generateLine();

        System.out.printf("Hello, I'm %s\n", BOT_NAME);
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a line of length LINE_LENGTH.
     */
    public static void generateLine() {
        System.out.println(String.format("%" + LINE_LENGTH + "s", "").replace(" ", "-"));
    }

    public static void displayExitText() {
        System.out.println("Goodbye.");
    }

    public static void displayAddTaskMessage(Task task) {
        System.out.printf("Task added: %s\n", task);
        System.out.printf("You now have %d task(s) in your list\n", TaskList.getTaskCount());
    }

    public static void displayDeleteTaskMessage(Task task) {
        System.out.printf("Task deleted: %s\n", task);
        System.out.printf("You now have %d task(s) in your list\n", TaskList.getTaskCount());
    }

    public static void displayMarkTaskMessage(Task task, boolean isDone) {
        System.out.printf("Task marked as %s: %s\n", isDone ? "complete" : "incomplete", task);
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
