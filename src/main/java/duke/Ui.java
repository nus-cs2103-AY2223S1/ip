package duke;

import duke.exception.DukeException;
import duke.processor.TaskList;
import duke.task.Task;

/**
 * Class that represents the duke.Ui, used to print
 * statements for the bot.
 *
 * @author Melissa Anastasia Harijanto
 */
public class Ui {
    /** String that represents a line. */
    protected final String LINE = "____________________________________________________________";

    /**
     * Greets the user.
     */
    public void greet() {
        String greetings = "\nHello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(LINE + greetings + LINE);
    }

    /**
     * Prints a message when the user adds a task to the bot.
     *
     * @param task The task that is added.
     * @param amountOfTasks The total amount of tasks that has been added by the user.
     */
    public void addTask(Task task, int amountOfTasks) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + amountOfTasks + " tasks in the list.");
        System.out.println(LINE);

    }

    /**
     * Prints the list of tasks added by the user.
     *
     * @param tasks The list of tasks to be printed.
     */
    public void list(TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        int count = 1;

        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            Task task = tasks.getTask(i);
            String taskName = task.toString();
            System.out.println(count + "." + taskName);
            count++;
        }

        System.out.println(LINE);
    }

    /**
     * Prints a message when a task is marked as done.
     *
     * @param task The task that is marked as undone.
     */
    public void mark(Task task){
        String taskName = task.toString();

        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskName);
        System.out.println(LINE);
    }

    /**
     * Prints a message when a task is marked as undone.
     *
     * @param task The task that is marked as undone.
     */
    public void unmark(Task task){
        String taskName = task.toString();

        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskName);
        System.out.println(LINE);
    }

    /**
     * Prints a message when the bot exits.
     */
    public void exit(){
        String exitLine = "Bye. Hope to see you again soon!";
        System.out.println(LINE + "\n" + exitLine + "\n" + LINE);
    }

    /**
     * Prints a message when the bot finds a task with a keyword.
     */
    public void find(TaskList tasks) {
        System.out.println(LINE);
        if (tasks.getNumberOfTasks() > 0) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
                int count = i + 1;
                Task task = tasks.getTask(i);
                System.out.println(count + "." + task.toString());
            }
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I can't find any tasks with that keyword :-(");
        }
        System.out.println(LINE);
    }

    /**
     * Prints a message when a user inputs a command that does not exist.
     */
    public void commandDoesNotExist(){
        System.out.println(LINE);
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(LINE);
    }

    /**
     * Prints a message when a user deletes their tasks.
     *
     * @param task The task that is to be deleted.
     * @param amountOfTasks The total amount of tasks that the user has left.
     */
    public void delete(Task task, int amountOfTasks) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + amountOfTasks + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints an error message.
     *
     * @param e The exception that is to be printed.
     */
    public void errorMessage(DukeException e) {
        System.out.println(LINE);
        System.out.println(e.toString());
        System.out.println(LINE);
    }
}
