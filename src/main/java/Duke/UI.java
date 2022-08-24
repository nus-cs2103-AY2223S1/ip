package Duke;

import Duke.Processor.TaskList;
import Duke.Task.Task;

/**
 * Class to represent the user interfaces.
 */
public class UI {
    private final static String underline = "____________________________________________________________";
    private final static String indentation = "  ";

    /**
     * Shows welcome message at the start of the program.
     */
    public void printGreetings() {
        System.out.println(indentation + underline);
        System.out.println(indentation + "Hello! I'm Duke\n" +
                indentation + "What can I do for you?");
        System.out.println(indentation + underline);
    }

    /**
     * Shows exit message at the end of the program.
     */
    public void exit() {
        System.out.println(indentation + underline);
        System.out.println(indentation + "Bye! Hope to see you again soon!");
        System.out.println(indentation + underline);
    }

    /**
     * Shows the added task.
     *
     * @param tasks TaskList.
     * @param numOfTask Number of Tasks.
     */
    public void showAddOnTask(TaskList tasks, int numOfTask) {
        System.out.println(indentation + underline);
        System.out.println(indentation + "I've added this task:");
        System.out.println(indentation + tasks.get(numOfTask));
        System.out.println(indentation + "Now you have " + (numOfTask + 1) + " tasks in the list.");
        System.out.println(indentation + underline);
    }

    /**
     * Shows the deleted task.
     *
     * @param tasks Tasklist.
     * @param taskDeleted Deleted task.
     */
    public void showDeleteTask(TaskList tasks, Task taskDeleted) {
        System.out.println(indentation + underline);
        System.out.println(indentation + "Noted. I've removed this task:");
        System.out.println(indentation + taskDeleted);
        System.out.println(indentation + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(indentation + underline);
    }

    /**
     * Shows the marked done task.
     *
     * @param tasks TaskList.
     * @param num Index of the specified task.
     */
    public void showDoneTask(TaskList tasks, int num) {
        System.out.println(indentation + underline);
        System.out.println(indentation + "Nice! I've marked this task as done:");
        System.out.println(indentation + tasks.get(num));
        System.out.println(indentation + underline);
    }

    /**
     * Shows the marked undone task.
     *
     * @param tasks TaskList.
     * @param num Index of the specified task.
     */
    public void showUndoneTask(TaskList tasks, int num) {
        System.out.println(indentation + underline);
        System.out.println(indentation + "OK, I've marked this task as not done yet:");
        System.out.println(indentation + tasks.get(num));
        System.out.println(indentation + underline);
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks TaskList.
     */
    public void showListDetails(TaskList tasks) {
        System.out.println(indentation + underline);
        System.out.println(indentation + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(indentation + (i + 1) + "." + indentation + tasks.get(i));
        }
        System.out.println(indentation + underline);
    }
}
