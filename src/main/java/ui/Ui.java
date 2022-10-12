package ui;

import exceptions.InvalidTaskIndexException;
import exceptions.NoTasksException;
import objects.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {

    public Ui() {}

    public static String printIntroduction() {
        return "Hello there! My name's Duck...\nPlease type in a command...";
    }

    /**
     * Prints out all the added tasks.
     */
    public static String showTasks(List<Task> tasks) throws NoTasksException {
        if (tasks.isEmpty()) {
            throw new NoTasksException();
        }
        StringBuilder text = new StringBuilder();
        int id = 1;
        for (Task task: tasks) {
            text.append(id).append(".").append(task.toString()).append("\n");
            id += 1;
        }
        return text.toString();
    }

    /**
     * Marks the task at the taskIndex in the list as done.
     * @param taskIndex position of the task in the list (1-indexed)
     */
    public String markTaskAsDone(int taskIndex, List<Task> tasks)
            throws NoTasksException, InvalidTaskIndexException {
        if (tasks.isEmpty()) {
            throw new NoTasksException();
        }
        if (taskIndex > tasks.size() || taskIndex <= 0) {
            throw new InvalidTaskIndexException();
        }
        Task currTask = tasks.get(taskIndex - 1); // label starting from 1
        currTask.markAsDone();
        return "Quack! I've marked this task as done:\n" + "  " + currTask;
    }

    /**
     * Marks the task at the taskIndex in the list as not done.
     * @param taskIndex position of the task in the list (1-indexed)
     */
    public String markTaskAsNotDone(int taskIndex, List<Task> tasks)
            throws NoTasksException, InvalidTaskIndexException {
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
        return "Quack, I've marked this task as not done yet:\n" + "  " + currTask;
    }

    /**
     * Prints a comment on the number of tasks added so far.
     */
    public static String printNumberOfTasks(List<Task> tasks) {
        if (tasks.size() == 1) {
            return "Now you have " + tasks.size() + " task in the list, quack!";
        } else {
            return "Now you have " + tasks.size() + " tasks in the list, quack!";
        }
    }
}
