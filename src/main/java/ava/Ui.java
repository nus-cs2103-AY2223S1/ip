package ava;

import ava.processor.TaskList;
import ava.task.Task;

/**
 * Class to represent the user interfaces.
 */
public class Ui {
    private final static String UNDERLINE = "____________________________________________________________";
    private final static String INDENTATION = "  ";

    /**
     * Shows welcome message at the start of the program.
     */
    public void printGreetings() {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Hello! I'm Ava\n"
                + INDENTATION + "What do you want to do?");
        System.out.println(INDENTATION + UNDERLINE);
    }

    /**
     * Shows exit message at the end of the program.
     */
    public void exit() {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Bye! Good luck on your tasks!");
        System.out.println(INDENTATION + UNDERLINE);
    }

    /**
     * Shows the added task.
     *
     * @param tasks TaskList.
     * @param numOfTask Number of Tasks.
     */
    public void showAddOnTask(TaskList tasks, int numOfTask) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "I've added this task:");
        System.out.println(INDENTATION + tasks.get(numOfTask));
        System.out.println(INDENTATION + "Now you have " + (numOfTask + 1) + " tasks in the list.");
        System.out.println(INDENTATION + UNDERLINE);
    }

    /**
     * Shows the deleted task.
     *
     * @param tasks Tasklist.
     * @param taskDeleted Deleted task.
     */
    public void showDeleteTask(TaskList tasks, Task taskDeleted) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Noted. I've removed this task:");
        System.out.println(INDENTATION + taskDeleted);
        System.out.println(INDENTATION + "Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(INDENTATION + UNDERLINE);
    }

    /**
     * Shows the marked done task.
     *
     * @param tasks TaskList.
     * @param num Index of the specified task.
     */
    public void showDoneTask(TaskList tasks, int num) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Nice job! I've marked this task as done:");
        System.out.println(INDENTATION + tasks.get(num));
        System.out.println(INDENTATION + UNDERLINE);
    }

    /**
     * Shows the marked undone task.
     *
     * @param tasks TaskList.
     * @param num Index of the specified task.
     */
    public void showUndoneTask(TaskList tasks, int num) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "It's OK, I've marked this task as not done yet:");
        System.out.println(INDENTATION + tasks.get(num));
        System.out.println(INDENTATION + UNDERLINE);
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks TaskList.
     */
    public void showListDetails(TaskList tasks) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(INDENTATION + (i + 1) + "." + INDENTATION + tasks.get(i));
        }
        System.out.println(INDENTATION + "Jiayous!");
        System.out.println(INDENTATION + UNDERLINE);
    }

    /**
     * Shows the find details.
     * @param tasks TaskList.
     * @param description Find target.
     */
    public void showFindDetails(TaskList tasks, String description) {
        System.out.println(INDENTATION + UNDERLINE);
        System.out.println(INDENTATION + "Here are the matching tasks in your list:");
        int increment = 1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(description)) {
                System.out.println(INDENTATION + (increment) + "." + INDENTATION + tasks.get(i));
                increment++;
            }
        }
        System.out.println(INDENTATION + UNDERLINE);
    }
}
