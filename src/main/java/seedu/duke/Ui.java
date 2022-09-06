package seedu.duke;

import seedu.duke.task.Task;

/**
 * Class for printing messages for user to view.
 */
public class Ui {
    private static final String LOGO  = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public Ui() {}

    /**
     * Greets a first time user.
     */
    public static void greetNew() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Nice to meet you, Master.\n" +
                "I have created a new list for you.");
    }

    /**
     * Greets a returning user.
     */
    public static void greetReturning() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Welcome back, Master.\n");
    }

    /**
     * Greets a leaving user.
     */
    public static void exit() {
        System.out.println("Goodbye! Thank you for visiting\n" + LOGO);
    }

    /**
     * Confirms a list has been saved.
     * @param list
     */
    public static void saved(TaskList list) {
        System.out.println("I have saved your list:\n" + list.toString());
    }

    /**
     * Prints the list.
     * @param list
     * @throws DukeException
     */
    public static void printList(TaskList list) throws DukeException {
        if (list.isEmpty()) {
            throw new DukeException("There is nothing in your list yet, Master.");
        } else {
            System.out.println("Here is your to-do list, Master:\n" +
                    list.toString());
        }
    }

    /**
     * Confirms that a task has been marked as done, or informs the user that the task
     * had already been done.
     * @param list
     * @param index
     * @param success
     * @throws DukeException
     */
    public static void marked(TaskList list, int index, boolean success) throws DukeException {
        Task curr = list.get(index);
        if (success) {
            System.out.println("Well done, Master! I have marked "
                    + curr.toString() +
                    " as done.");
        } else {
            throw new DukeException("This task has already been marked done, Master.");
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
    public static void unmarked(TaskList list, int index, boolean success) throws DukeException {
        Task curr = list.get(index);
        if (success) {
            System.out.println("Oh no :( I have marked " +
                    curr.toString()
                    + " as undone, Master.");
        } else {
            throw new DukeException("This duke.task was already marked undone, Master.");
        }
    }

    /**
     * Confirms that a task has been deleted.
     * @param task
     */
    public static void deleted(Task task) {
        System.out.println("Very well, I have deleted " + task.toString() + " from your list, Master.");
    }

    /**
     * Confirms that a task has been added.
     * @param task
     */
    public static void added(Task task) {
        System.out.println("I have added " + task.toString() + " to the list, Master.");
    }

    public static void found(String searchString, TaskList foundList) {
        if (foundList.size() == 1) {
            System.out.println("I have found the following task matching \"" + searchString + "\"");
            System.out.println(foundList);
        } else {
            System.out.println("I have found the following tasks matching \"" + searchString + "\"");
            System.out.println(foundList);
        }
    }

    public static void notFound(String searchString) {
        System.out.println("Sorry, Master." +
                "I was not able to find any tasks matching \"" + searchString + "\"");
    }

}
