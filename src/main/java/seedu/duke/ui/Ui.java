package seedu.duke.ui;

import seedu.duke.exception.DukeException;
import seedu.duke.list.FoundList;
import seedu.duke.list.TaskList;
import seedu.duke.task.Task;

/**
 * Class for printing messages for user to view.
 */
public class Ui {
    private static final String LOGO  =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public Ui() {}

    /**
     * Greets a first time user.
     */
    public static String greetNew() {
        return "Hello from\n" + LOGO + "Nice to meet you, Master.\n"
                + "I have created a new list for you.";
    }

    /**
     * Greets a returning user.
     */
    public static String greetReturning() {
        return "Hello from\n" + LOGO + "Welcome back, Master.\n";
    }

    public static String greet() {
        return "Hello from\n" + LOGO;
    }

    /**
     * Greets a leaving user.
     */
    public static String exit() {
        return "Goodbye! Thank you for visiting\n" + LOGO;
    }

    /**
     * Confirms a list has been saved.
     * @param list
     */
    public static String saved(TaskList list) {
        return "I have saved your list:\n" + list.toString();
    }

    /**
     * Prints the list.
     * @param list
     * @throws DukeException
     */
    public static String printList(TaskList list) throws DukeException {
        if (list.isEmpty()) {
            throw new DukeException("There is nothing in your list yet, Master.");
        } else {
            return "Here is your to-do list, Master:\n" +
                    list.toString();
        }
    }

    /**
     * Makes a cuppa to make the user's day.
     * @return
     */
    public static String makeCoffee() {
        return "I have prepared a steaming cup of coffee for you, Master. Enjoy it while it's hot!";
    }

    /**
     * Confirms that a task has been marked as done, or informs the user that the task
     * had already been done.
     * @param list
     * @param index
     * @param success
     * @throws DukeException
     */
    public static String marked(TaskList list, int index, boolean success) throws DukeException {
        Task curr = list.get(index);
        if (success) {
            return "Well done, Master! I have marked "
                    + curr.toString() +
                    " as done.";
        } else {
            throw new DukeException("This task was already marked done, Master.");
        }
    }

    /**
     * Confirms that a task has been marked as undone, or informs the user that the task
     * had already been marked undone.
     * @param list
     * @param index
     * @param success
     * @throws DukeException
     */
    public static String unmarked(TaskList list, int index, boolean success) throws DukeException {
        Task curr = list.get(index);
        if (success) {
            return "Oh no :( I have marked " +
                    curr.toString()
                    + " as undone, Master.";
        } else {
            throw new DukeException("This task was already marked undone, Master.");
        }
    }

    /**
     * Confirms that a task has been deleted.
     * @param task
     */
    public static String deleted(Task task) {
        return "Very well, I have deleted " + task.toString() + " from your list, Master.";
    }

    /**
     * Confirms that a task has been added.
     * @param task
     */
    public static String added(Task task) {
        return "I have added " + task.toString() + " to the list, Master.";
    }


    /**
     * Confirms that the name of a task has been edited.
     * @param index
     * @param task
     * @return
     */
    public static String nameEdited(int index, Task task) {
        return "I have edited task " + index + " to " + task.toString() + ", Master.";
    }

    /**
     * Confirms that the time for an Event or Deadline task has been edited.
     * @param index
     * @param task
     * @return
     */
    public static String timeEdited(int index, Task task) {
        return "I have edited task " + index + " to " + task.toString() + ", Master.";
    }

    /**
     * Returns the result of a search.
     * @param searchString
     * @param foundList
     * @return
     */
    public static String found(String searchString, FoundList foundList) {
        if (foundList.size() == 1) {
            return "I have found the following task matching \"" + searchString + "\":\n"
                    + foundList;
        } else {
            return "I have found the following tasks matching \"" + searchString + "\":\n"
                    + foundList;
        }
    }

    /**
     * Informs user that no items matching search were found.
     * @param searchString
     */
    public static String notFound(String searchString) {
        return "Sorry, Master." +
                "I was not able to find any tasks matching \"" + searchString + "\"";
    }

}
