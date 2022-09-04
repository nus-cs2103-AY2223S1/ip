package candice;

import candice.task.Task;
import candice.task.TaskList;

/**
 * Prints out visual response to update the user on what is going on.
 */
public class Ui {
    /** A line break so that responses are more easily distinguished */
    private static final String LINE_BREAK = "------------------------------------------------------------";

    /**
     * Prints a message for when the Candice program starts running.
     */
    public static void printMessageForStartingUp() {
        System.out.println(LINE_BREAK);
        System.out.println("Sup bro! My name is Candice.");
        System.out.println("I'm here to help you track your tasks!");
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints a message for when the Candice program shuts down.
     */
    public static void printMessageForShuttingDown() {
        System.out.println(LINE_BREAK);
        System.out.println("Bye bro. See you soon.");
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints a message for when a task is added to the task list.
     *
     * @param newTask The task that was added to the task list.
     * @param taskList The task list that the task was added to.
     */
    public static void printMessageForAddTask(Task newTask, TaskList taskList) {
        System.out.println(LINE_BREAK);
        System.out.println("I gotchu fam. Your task has been added:");
        System.out.println("  " + newTask.getStatus());
        System.out.println("You have " + taskList.getLength() + " task(s). Stop procrastinating bro.");
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints a message for when a task is deleted from the task list.
     *
     * @param removedTask The task that was removed from the task list.
     * @param taskList The task list that the task was removed from.
     */
    public static void printMessageForDeleteTask(Task removedTask, TaskList taskList) {
        System.out.println(LINE_BREAK);
        System.out.println("I hope you're not deleting this just to avoid work:");
        System.out.println("  " + removedTask.getStatus());
        System.out.println("You have " + taskList.getLength() + " task(s). Stop procrastinating bro.");
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints a message for when a task has been marked as finished.
     *
     * @param selectedTask The task that was marked as finished.
     */
    public static void printMessageForMarkTask(Task selectedTask) {
        System.out.println(LINE_BREAK);
        System.out.println("Nice one lah, finally doing your work.");
        System.out.println(selectedTask.getStatus());
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints a message for when a task has been marked as unfinished.
     *
     * @param selectedTask The task that was marked as unfinished.
     */
    public static void printMessageForUnmarkTask(Task selectedTask) {
        System.out.println(LINE_BREAK);
        System.out.println("What happened bro.");
        System.out.println(selectedTask.getStatus());
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints the current task list.
     *
     * @param taskList The task list to be printed.
     */
    public static void printMessageForList(TaskList taskList) {
        System.out.println(LINE_BREAK);
        System.out.println("Here are your tasks. You better start now:");
        System.out.println(taskList.toString());
        System.out.println(LINE_BREAK);
    }
}
