package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * The Ui class deals with interactions with users.
 */
public class Ui {

    /* The scanner that reads input*/
    private Scanner sc = new Scanner(System.in);

    /**
     * Reads the user's command.
     * @return The string representing the user's input.
     */
    public String readCommand() {
        String command = sc.nextLine();
        return command;
    }

    /**
     * Show the Duke's welcome message to the user.
     */
    public void showWelcome() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
    }

    /**
     * Show line to the user.
     */
    public void showLine() {
        System.out.println("_______");
    }

    /**
     * Show loading error to the user.
     */
    public void showLoadingError() {
        System.out.println("File not found.");
    }


    /**
     * Show a message that a specified task has been deleted from the list.
     * @param t The specified task deleted.
     * @param taskList The task list the specified task is deleted from.
     */
    public void showDelete(Task t, TaskList taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t.toString());
        if (taskList.getTasksNumber() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + taskList.getTasksNumber() + " tasks in the list.");
        }

    }

    /**
     * Show a message that a specified task has been added to the list.
     * @param t The specified task to be added.
     * @param taskList The task list the specified task is added to.
     */
    public void showAddTask(Task t, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        if (taskList.getTasksNumber() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + taskList.getTasksNumber() + " tasks in the list.");
        }
    }

    /**
     * Show a message that a specified task has been marked done.
     * @param t The specified task to be marked done.
     */
    public void showMark(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }

    /**
     * Show a message that a specified task has been marked undone.
     * @param t The specified task to be marked undone.
     */
    public void showUnmark(Task t) {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(t);
    }

    /**
     * Show all the tasks in the task list.
     * @param taskList The specified task list.
     */
    public void showTasks(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            System.out.println((i + 1) + "." + currTask.toString());
        }
    }

    /**
     * Show all tasks on a specified date.
     * @param onDateTasks ArrayList that represents all tasks on date.
     */
    public void showGetDate(ArrayList<Task> onDateTasks) {
        for (Task t : onDateTasks) {
            System.out.println(t);
        }
        int count = onDateTasks.size();
        if (count == 0) {
            System.out.println("YAY! You have no deadlines/events on this day.");
        } else if (onDateTasks.size() == 1) {
            System.out.println("Shag man. You have " + count + " deadline/event on this day.");
        } else {
            System.out.println("Shag man. You have " + count + " deadlines/events on this day.");
        }
    }

    /**
     * Show all tasks found with specified keyword.
     * @param foundTasks ArrayList that represents tasks containing specified keyword.
     */
    public void showFoundTasks(ArrayList<Task> foundTasks) {
        int count = foundTasks.size();
        if (count == 0) {
            System.out.println("There is no matching task in your list.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
        }

        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + foundTasks.get(i));
        }
    }

    /**
     * Show error message.
     * @param error The description for the error.
     */
    public void showError(String error) {
        System.out.println("OOPS!!!" + error);
    }

    /**
     * Show bye message to the user.
     */
    public void showBye() {
        System.out.println("Bye, hope to see you again next time!");
    }

}
