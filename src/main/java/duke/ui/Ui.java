package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.data.exception.DukeException;
import duke.tasks.Task;

/**
 * This class encapsulates the user interface
 */
public class Ui {

    private Scanner scanner;

    /**
     * Constructs a new Ui
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Greets the user whenever the application starts
     */
    public String printWelcome() {
        return "Hello! I'm Duke\nWhat can I do for you? ^_^";
    }

    /**
     * Exits when the user chooses to stop the application
     */
    public String printExit() {
        this.scanner.close();
        return "\tBye. Hope to see you again soon :D";
    }

    /**
     * Prints the task that is just added
     * @param addedTask The task that is just added
     * @param numOfTasks The number of tasks in the list
     */
    public String printAddTask(Task addedTask, int numOfTasks) {
        String header = "\tGot it! I have added this task:\n\t\t" + addedTask;
        String tasks = String.format("\n\tNow you have %d %s in the list!", numOfTasks,
                numOfTasks < 2 ? "task" : "tasks");
        return header + tasks;
    }

    /**
     * Prints the task that is just deleted
     * @param deletedTask The deleted task
     * @param numOfTasks The number of tasks remaining
     */
    public String printDeleteTask(Task deletedTask, int numOfTasks) {
        String msg = String.format("\tNoted, I have removed this task:\n\t\t%s", deletedTask);
        String tasks = String.format("\n\tNow you have %d %s in the list!", numOfTasks,
                numOfTasks < 2 ? "task" : "tasks");
        return msg + tasks;
    }

    /**
     * Prints the task that is marked as completed
     * @param task The task that is marked as completed
     */
    public String printMarkTask(Task task) {
        String msg = String.format("\tNice! I have marked this task as done:\n\t\t%s",
                task);
        return msg;
    }

    /**
     * Prints the task that is marked as not completed
     * @param task The task that is marked as not completed
     */
    public String printUnmarkTask(Task task) {
        String msg = String.format("\tOkay! I have marked this task as not done:\n\t\t%s",
                task);
       return msg;
    }

    /**
     * Prints the exception's message
     * @param exception The exception thrown and caught
     */
    public String printException(Exception exception) {
        return "\t" + exception.getMessage();
    }

    /**
     * Gets the input provided by the user
     * @return The input provided
     */
    public String input() {
        return this.scanner.nextLine();
    }

    /**
     * Notifies users about invalid commands
     * @throws DukeException If the command is invalid
     */
    public String printInvalid() throws DukeException {
        throw new DukeException("I don't know what this means :(");
    }

    /**
     * Prints the list of tasks
     * @param list The list of tasks
     */
    public String printList(String list) {
        return list;
    }

    public String printFind(ArrayList<Task> list) {
        StringBuilder stringBuilder = new StringBuilder("\tHere are the matching tasks in your list:");
        int count = 1;
        for (Task task: list) {
            String item = String.format("\n\t%d. %s", count, task);
            stringBuilder.append(item);
            count++;
        }

        return stringBuilder.toString();
    }

    public String print(String input) {
        return input;
    }
}
