package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Ui deals with interactions with the user.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class Ui {

    private static final String LOGO = "\t ____        _\n"
            + "\t|  _ \\ _   _| | _____\n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "\t____________________________________________________________";

    private Scanner sc;

    /**
     * A constructor for Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * A method that reads the next line of user input.
     *
     * @return The user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * A method that prints LINE.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * A method that prints the welcome message.
     */
    public void showWelcome() {
        System.out.printf("%s%n%s%s%s%s%n", LINE, LOGO, LINE, "\n\tHello! I'm Duke\n\tWhat can I do for you?\n", LINE);
    }

    /**
     * A method that prints the farewell message.
     */
    public void showBye() {
        System.out.println("\tBye. Hope to see you again soon!");
        sc.close();
    }

    /**
     * A method that prints the TaskList.
     *
     * @param tasks TaskList to be displayed.
     */
    public void showList(TaskList tasks) {
        System.out.printf("%s%n%s%n", "\tHere are the task(s) in your list: ", tasks.toString());
    }

    /**
     * A method that prints the mark-message.
     *
     * @param task The Task to be marked.
     */
    public void showMark(Task task) {
        System.out.printf("%s%s%n", "\tNice! I've marked this task as done:\n\t  ", task);
    }

    /**
     * A method that prints the un-mark-message.
     *
     * @param task The Task to be un-marked.
     */
    public void showUnmark(Task task) {
        System.out.printf("%s%s%n", "\tOK, I've marked this task as not done yet:\n\t  ", task);
    }

    /**
     * A method that prints the add-message.
     *
     * @param task The Task to be added.
     * @param size Size of the TaskList after Task has been added.
     */
    public void showAdd(Task task, int size) {
        System.out.printf("%s%s%s%s%s", "\tGot it. I've added this task:\n\t  ", task, "\n\tNow you have ",
                size, " task(s) in the list.\n");
    }

    /**
     * A method that prints the delete-message.
     *
     * @param task The Task to be deleted.
     * @param size Size of the TaskList after Task has been deleted.
     */
    public void showDelete(Task task, int size) {
        System.out.printf("%s%s%s%s%s", "\tNoted. I've removed this task:\n\t  ", task, "\n\tNow you have ",
                size, " task(s) in the list.\n");
    }

    /**
     * A method that prints the list of Task(s) containing the specified keyword.
     *
     * @param foundTasks The list of Task(s) to be displayed.
     */
    public void showFind(ArrayList<Task> foundTasks) {
        String lst = "";
        int size = foundTasks.size();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                lst += String.format("\t%d.%s", i + 1, foundTasks.get(i));
            } else {
                lst += String.format("\t%d.%s\n", i + 1, foundTasks.get(i));
            }
        }
        System.out.printf("%s%n%s%n", "\tHere are the matching task(s) in your list: ", lst);
    }

    /**
     * A method that prints the error message when data file cannot be found.
     */
    public void showLoadingError() {
        System.out.printf("%s%s", LINE, "\n\tSave data does not exist.\n");
    }

    /**
     * A method that prints an error message.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
