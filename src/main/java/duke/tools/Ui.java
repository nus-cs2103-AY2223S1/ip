package duke.tools;

import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.Scanner;

/**
 * This class deals with interactions with the user.
 */
public class Ui {
    /** Controls termination of Duke program */
    private Boolean isExit = false;
    /** Scanner object to read user input */
    private static Scanner sc = new Scanner(System.in);

    /**
     * Reads next line of user input command.
     *
     * @return String of user input command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Checks if Duke program is to terminate.
     *
     * @return If Duke program should continue.
     */
    public Boolean canContinue() {
        return !isExit;
    }

    /**
     * Prints greeting.
     */
    public void printGreeting() {
        System.out.println("Hello!\nHow may i help you today?");
    }

    /**
     * Prints farewell.
     */
    public void printFarewell() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    /**
     * Prints status of TaskList.
     * If TaskList is not empty, prints all content of TaskList.
     *
     * @param taskList List of task to be printed.
     * @throws DukeException
     */
    public void printListStatus(TaskList taskList) throws DukeException {
        if (taskList.getSize() == 0) {
            System.out.println("There are currently no tasks in your list");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.getSize(); i++) {
                System.out.printf("%d. %s\n", i + 1, taskList.getTask(i));
            }
        }
    }

    /**
     * Prints message of all task found containing keyword.
     *
     * @param taskList TaskList to search through with keyword.
     * @param keyword Keyword to find.
     * @throws DukeException
     */
    public void printTaskFind(TaskList taskList, String keyword) throws DukeException {
        System.out.println("Here are the matching tasks in your list:");
        int findCount = 0;
        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i).findInDescription(keyword)) {
                System.out.println(String.format("%d. %s", i + 1, taskList.getTask(i)));
                findCount++;
            }
        }
        if (findCount == 0) {
            System.out.println("Oh no, there are no matching tasks found :(");
        } else {
            System.out.printf("There are %d matching tasks found\n", findCount);
        }
    }

    /**
     * Prints message marking task as done in TaskList.
     *
     * @param index Index of task that is marked.
     * @param task Task marked as done.
     */
    public void markAsDone(int index, Task task) {
        System.out.println("Nice! I've marked this task as done:\n"
                + String.format("%d. %s", index + 1, task));
    }

    /**
     * Prints message marking task as undone in TaskList.
     *
     * @param index Index of task that is marked.
     * @param task Task marked as undone.
     */
    public void markAsUndone(int index, Task task) {
        System.out.println("Ok! I've marked this task as not done yet:\n"
                + String.format("%d. %s", index + 1, task));
    }

    /**
     * Prints message of deleting task from TaskList.
     *
     * @param index Index of task that is deleted.
     * @param task Task that was deleted.
     */
    public void deleteTask(int index, Task task) {
        System.out.println("Noted. I've removed this task:\n"
                + String.format("%d. %s", index + 1, task));
    }

    /**
     * Prints message of adding task to TaskList.
     *
     * @param task Task that was added.
     */
    public void addTask(Task task) {
        System.out.println("Got it! I've added this task:\n"
                + "> " + task);
    }

    /**
     * Prints message of number of task in TaskList.
     *
     * @param taskList TaskList stored in Duke.
     */
    public void printListSize(TaskList taskList) {
        System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
    }

    /**
     * Prints message of DukeException that occurred.
     *
     * @param e DukeException containing error message.
     */
    public void printException(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Terminates Duke program.
     */
    public void exit() {
        isExit = true;
    }
}
