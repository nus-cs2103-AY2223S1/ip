package rick;

import java.util.ArrayList;
import java.util.Scanner;

import rick.tasks.Task;
import rick.tasks.TaskList;

/**
 * Ui class deals with interactions with the user.
 * It reads the user input and contains the methods to print the output.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the user input.
     */
    public final String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Adds a line break between the text.
     */
    public static String addLineBreak(String text) {
        return "\n" + text + "\n";
    }

    /**
     * Returns the welcome message.
     */
    public String showWelcome() {
        return "Hello, I'm Rick! \n What can I do for you?";
    }

    /**
     * Returns the error message when a RickException is caught
     */
    public String showError(RickException error) {
        return addLineBreak(error.getMessage());
    }

    /**
     * Prints the output text to user
     */
    public void print(String text) {
        System.out.println(text);
    }

    /**
     * Returns an empty line to demarcate the end of an output.
     */
    public String showLine() {
        return "____________________________________________________________\n";
    }

    /**
     * Returns the text representation of the task list.
     */
    public String showArray(TaskList taskList) {
        ArrayList<Task> list = taskList.getList();
        if (list.size() == 0) {
            return addLineBreak("You have no tasks in your list.");
        } else {
            String result = "Here are the tasks in your list:\n";
            for (int i = 0; i < list.size(); i++) {
                result += "\t" + (i + 1) + ". " + list.get(i) + "\n";
            }
            return addLineBreak(result);
        }
    }

    /**
     * Returns the text representation of the tasks that contain the specified
     * keyword.
     */
    public String showMatchingTasks(ArrayList<Task> found) {
        if (found.size() == 0) {
            return addLineBreak("No matching tasks found.");
        } else {
            String result = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < found.size(); i++) {
                result += "\t" + (i + 1) + ". " + found.get(i) + "\n";
            }
            return addLineBreak(result);
        }
    }

    /**
     * Returns the exit message for when user exits Rick.
     */
    public static String showExitMessage() {
        return addLineBreak("Goodbye Morty! Hope to see you again!");
    }

    /**
     * Returns the message when a task is added to the todo list
     */
    public String showAddMessage(Task task, TaskList taskList) {
        return addLineBreak(
                "Got it. I've added this task:\n" + task + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Returns the message when a task is removed from the todo list
     */
    public String showDeleteMessage(Task task) {
        return addLineBreak("Noted. I've removed this task:\n" + task);
    }

    /**
     * Returns the message when a task is marked as done
     */
    public String showMarkMessage(Task task) {
        return addLineBreak("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Returns the message when a task is marked as undone
     */
    public String showUnmarkMessage(Task task) {
        return addLineBreak("Okay, I've unmarked this task as undone:\n" + task);
    }
}
