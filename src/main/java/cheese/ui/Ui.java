package cheese.ui;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import cheese.data.TaskList;
import cheese.task.Task;

/**
 * Represents user interface that interacts with user.
 */
public class Ui {
    /** Scanner that reads user input. */
    private final Scanner in;

    /** Output stream that prints to user. */
    private final PrintStream out;

    /**
     * Constructs an instance of <code>Ui</code>.
     */
    public Ui() {
        in = new Scanner(System.in);
        out = System.out;
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        out.println("Woof! I'm Cheese, your puppy assistant.\n" + "What can I do for you?");
    }

    /**
     * Reads input from user.
     * 
     * @return User input.
     */
    public String readCommand() {
        showLine();
        out.print("~ ");
        String fullCommand = in.nextLine();
        showLine();
        return fullCommand;
    }

    /**
     * Prints message after adding task to list.
     * 
     * @param addedTask Task that was added.
     * @param newListSize New task list size after adding task.
     */
    public void showAddTask(Task addedTask, int newListSize) {
        out.println("Gotcha! I have a paw-fect memory!");
        out.println("  " + addedTask);
        out.println("You have " + newListSize + " task(s) in the list.");
    }

    /**
     * Prints message after deleting task from list.
     * 
     * @param addedTask Task that was deleted.
     * @param newListSize New task list size after deleting task.
     */
    public void showDeleteTask(Task deletedTask, int remainingListSize) {
        out.println("Gotcha! I'll forget about this task!");
        out.println("  " + deletedTask);
        out.println("You have " + remainingListSize + " task(s) remaining.");
    }

    /**
     * Prints message after marking task as complete.
     * 
     * @param taskDone Task that was marked as complete.
     */
    public void showMarkTaskAsDone(Task taskDone) {
        out.println("Paw-some! Another task done!");
        out.println("  " + taskDone);
    }

    /**
     * Prints message after marking task as incomplete.
     * 
     * @param taskNotDone Task that was marked as incomplete.
     */
    public void showMarkTaskAsNotDone(Task taskNotDone) {
        out.println("Okay, I've marked this task as not done yet.");
        out.println("  " + taskNotDone);
    }

    /**
     * Prints task list.
     * 
     * @param taskList Task list to print.
     */
    public void showTaskList(TaskList taskList) {
        out.println(taskList);
    }

    /**
     * Prints search result.
     * 
     * @param searchResult List of searched tasks to print.
     */
    public void showSearchResult(ArrayList<Task> searchResult) {
        for (int i = 0; i < searchResult.size(); i++) {
            out.println((i + 1) + ". " + searchResult.get(i));
        }
    }

    /**
     * Prints goodbye message.
     */
    public void showGoodbye() {
        out.println("Going so soon? :') Bye");
    }

    /**
     * Prints error message.
     * 
     * @param errorMessage Message to print.
     */
    public void showError(String errorMessage) {
        out.println(errorMessage);
    }

    /**
     * Prints line divider to distinguish between user input and program output.
     */
    public void showLine() {
        out.println("-----");
    }
}
