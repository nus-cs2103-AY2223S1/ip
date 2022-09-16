package ploopy.ui;

import ploopy.task.Task;

/**
 * Interacts with the user between commands
 *
 */
public class TextUI {
    /** Greeting message */
    private static final String GREETING = "Hi, I'm Ploopy! Nice to meet you! Whats up?";
    /** Completed task message */
    private static final String COMPLETED_TASK = "Nice! You've completed this task. I'll mark it as done.";
    /** Incomplete task message */
    private static final String INCOMPLETE_TASK = "Alright this task has been marked as undone.";
    /* Added task message */
    private static final String ADDED_TASK = "I've added this task to your list. Here you go: ";
    /** No input message */
    private static final String NO_INPUT = "You didn't say what I should do!";
    /** Empty command message */
    private static final String EMPTY_COMMAND = "What should I do with the ";
    /** Nonsense input message */
    private static final String NONSENSE_INPUT = "I can't do that right now, sorry";
    /** Storage file error message */
    private static final String IO_ERROR = "Something went wrong creating your files!";


    /**
     * Prints a greeting to the user.
     *
     */
    public static String greeting() {
        return GREETING;
    }

    /**
     * Tells the user the necessary input format
     * to prevent input mistakes.
     */
    public static String correctFormatMessage() {
        return "Please use the following format:" + "\n"
                + "<task type> <task name> /<at or by> <date and time>" + "\n"
                + "e.g: event concert /at 12/12/2020 1800";
    }

    /**
     * Acknowledges the specific task has been added and
     * tells the user how many tasks are left.
     *
     * @param task Task that has been added.
     * @param totalTasks Number of tasks remaining after adding.
     */
    public static String addTaskMessage(Task task, int totalTasks) {
        return String.format("%s%s You have %d tasks in your list",
                ADDED_TASK, task, totalTasks);
    }

    /**
     * Tells the user that a certain task has been marked as done.
     *
     * @param task The task that has been completed.
     */
    public static String markTaskMessage(Task task) {
        return COMPLETED_TASK + "\n" + task;
    }

    /**
     * Tells the user that a certain task has been marked as undone.
     *
     * @param task The task that is marked incomplete.
     */
    public static String unmarkTaskMessage(Task task) {
        return INCOMPLETE_TASK + "\n" + task;
    }

    /**
     * Tells the user that a certain task has been deleted and
     * how many tasks are left.
     *
     * @param task The deleted task.
     * @param index The number of tasks remaining.
     */
    public static String deleteTaskMessage(Task task, int index) {
        return String.format("Deleted %s\nYou have %d task(s) remaining.", task, index);
    }

    /**
     * Tells the user the database file is being created.
     * @return the message
     */
    public static String createFilesMessage() {
        return "Creating necessary files...";
    }

    public static String addingFilesMessage() {
        return "Adding existing tasks...";
    }

    /**
     * Prints the correct message to the user according
     * to the exception tag.
     *
     * @param type The type and data of the exception.
     */
    public static String exceptionMessage(String type) {
        switch (type) {
        case "nonsense":
            return NONSENSE_INPUT;
        case "blank":
            return NO_INPUT;
        case "IO":
            return IO_ERROR;
        case "Wrong Format":
            return correctFormatMessage();
        default:
            return EMPTY_COMMAND + type;
        }
    }

    public static String foundTasksMessage() {
        return "Found these tasks";
    }

    public static String noTasksFoundMessage() {
        return "No tasks found";
    }

    public static String changeTaskPriorityMessage(Task task) {
        return "Changed this task's priority: " + task;
    }

}
