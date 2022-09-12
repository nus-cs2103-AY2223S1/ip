package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * A class that deals with user interaction with the program.
 */
public class Ui {

    private final Scanner sc;

    /**
     * Creates the Ui.
     * Initialises the Scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the String introduction message to Duke program.
     */
    public static String printIntro() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello! I'm GigaDuke\n");
        sb.append("What can I do for you?");
        return sb.toString();
    }

    /**
     * Returns the String exit message of Duke program.
     *
     * @return Exit message.
     */
    public String printExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a String containing all the Tasks at hand.
     *
     * @param tasks A list of all the Tasks at hand.
     * @return String showcasing all Tasks.
     */
    public String printTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            String task = String.format(" %d. %s\n", i + 1, tasks.getTask(i));
            sb.append(task);
        }
        return sb.toString();
    }

    /**
     * Returns the String message that a Task has been added.
     *
     * @param task The Task to be added.
     * @param tasks The list of all Tasks.
     * @return String message indicating task has been added.
     */
    public String printAddTasks(Task task, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        String taskToBeAdded = String.format("  %s\n", task.toString());
        String numberOfTasksLeft = String.format("Now you have %d tasks in the list.", tasks.getSize());
        sb.append(taskToBeAdded);
        sb.append(numberOfTasksLeft);
        return sb.toString();
    }

    /**
     * Returns a String message that a Task was marked done.
     *
     * @param task The Task that has been marked.
     * @return String message indicating the Task has been marked.
     */
    public String printMark(Task task) {
        return String.format("Nice! I've marked this task as done:\n %s", task.toString());
    }

    /**
     * Returns a String message that a Task was marked not done.
     *
     * @param task The Task that has been marked.
     * @return String message indicating that Task has been unmarked.
     */
    public String printUnMark(Task task) {
        return String.format("OK, I've marked this task as not done yet:\n %s", task.toString());
    }

    /**
     * Returns a String message that Task has been deleted.
     *
     * @param task The Task that has been deleted.
     * @param tasks The list of all Tasks.
     * @return String message indicating that Task has been deleted.
     */
    public String printDeleteTask(Task task, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        String taskToBeDeleted = String.format("  %s\n", task.toString());
        String numberOfTasksLeft = String.format("Now you have %d tasks in the list.", tasks.getSize());
        sb.append(taskToBeDeleted);
        sb.append(numberOfTasksLeft);
        return sb.toString();
    }

    /**
     * Returns String of the Exception message.
     *
     * @param e The exception caught.
     * @return String message of exception.
     */
    public String printException(Exception e) {
        return e.getMessage();
    }

    /**
     * Returns a String message containing all the Tasks that matches the filter.
     *
     * @param tasks The list of Tasks that match filter.
     * @return String message showcasing all the Tasks that has been filtered.
     */
    public String printFilteredTasks(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            String task = String.format(" %d. %s\n", i + 1, tasks.get(i));
            sb.append(task);
        }
        return sb.toString();
    }

    /**
     * Returns a String message that a task's date field has been successfully updated.
     *
     * @param task The task to be updated.
     * @return String message indicating the update's success.
     */
    public String printUpdateTask(Task task) {
        return "OK, I have updated the task to:\n\t" + task.toString();
    }
}
