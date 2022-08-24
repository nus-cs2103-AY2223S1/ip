package duke;

import duke.processor.TaskList;
import duke.task.Task;

/**
 * Class to represent the user interfaces
 */
public class Ui {
    private static final String UNDERLINE = "____________________________________________________________";
    private static final String INDENTATION = "  ";

    /**
     * Method to show Welcome message at the start.
     */
    public void printGreetings() {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Hello! I'm Duke\n"
                + INDENTATION + "What can I do for you?");
        System.out.println(INDENTATION + UNDERLINE);
    }

    /**
     * Method to show Exit message at finish.
     */
    public void exit() {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Bye! Hope to see you again soon!");
        System.out.println(INDENTATION + UNDERLINE);
    }

    /**
     * The method to show added task.
     * @param task
     */
    public void showAddOnTask(TaskList task, int numOfTask) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "I've added this task:");
        System.out.println(INDENTATION + task.get(numOfTask));
        System.out.println(INDENTATION + "Now you have " + (numOfTask + 1) + " tasks in the list.");
        System.out.println(INDENTATION + UNDERLINE);
    }

    /**
     * The method to show added task.
     * @param task
     */
    public void showDeleteTask(TaskList task, Task taskDeleted) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Noted. I've removed this task:");
        System.out.println(INDENTATION + taskDeleted);
        System.out.println(INDENTATION + "Now you have " + task.size() + " tasks in the list.");
        System.out.println(INDENTATION + UNDERLINE);
    }

    /**
     * The method to show task being done.
     * @param task
     */
    public void showDoneTask(TaskList task, int num) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + task.get(num));
        System.out.println(INDENTATION + UNDERLINE);
    }

    /**
     * The method to show task being undone.
     * @param task
     */
    public void showUndoneTask(TaskList task, int num) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "OK, I've marked this task as not done yet:");
        System.out.println(INDENTATION + task.get(num));
        System.out.println(INDENTATION + UNDERLINE);
    }

    /**
     * The method to show list of added tasks.
     * @param task
     */
    public void showListDetails(TaskList task) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        for (int i = 0; i < task.size(); i++) {
            System.out.println(INDENTATION + (i + 1) + "." + INDENTATION + task.get(i));
        }
        System.out.println(INDENTATION + UNDERLINE);
    }
}
