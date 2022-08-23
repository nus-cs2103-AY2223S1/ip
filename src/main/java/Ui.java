import exceptions.InvalidTaskIndexException;
import exceptions.NoTasksException;
import objects.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {

    public Ui() {}

    public static void printIntroduction() {
        System.out.println("Hello there! My name's Duck...");
        System.out.println("Please type in a command...");
    }

    /**
     * Prints out all the added tasks.
     */
    public static void showTasks(List<Task> tasks) throws NoTasksException {
        if (tasks.isEmpty()) {
            throw new NoTasksException();
        }
        int id = 1;
        for (Task task: tasks) {
            System.out.println(id + "." + task.toString());
            id += 1;
        }
    }

    /**
     * Marks the task at the taskIndex in the list as done.
     * @param taskIndex position of the task in the list (1-indexed)
     */
    public void markTaskAsDone(int taskIndex, List<Task> tasks) throws NoTasksException, InvalidTaskIndexException {
        if (tasks.isEmpty()) {
            throw new NoTasksException();
        }
        if (taskIndex > tasks.size() || taskIndex <= 0) {
            throw new InvalidTaskIndexException();
        }
        Task currTask = tasks.get(taskIndex - 1); // label starting from 1
        currTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + currTask);
    }

    /**
     * Marks the task at the taskIndex in the list as not done.
     * @param taskIndex position of the task in the list (1-indexed)
     */
    public void markTaskAsNotDone(int taskIndex, List<Task> tasks) throws NoTasksException, InvalidTaskIndexException {
        if (tasks.isEmpty()) {
            throw new NoTasksException();
        }
        if (taskIndex > tasks.size() || taskIndex <= 0) {
            throw new InvalidTaskIndexException();
        }
        Task currTask = tasks.get(taskIndex - 1); // label starting from 1
        currTask.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + currTask);
    }

    /**
     * Prints a comment on the number of tasks added so far.
     */
    public static void printNumberOfTasks(List<Task> tasks) {
        if (tasks.size() == 1) {
            System.out.println("Now you have " + tasks.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    public static void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Exits the app.
     * @param input scanner object
     */
    public static void endSession(Scanner input) {
        System.out.println("Bye! See you next time!");
        input.close();
    }
}
