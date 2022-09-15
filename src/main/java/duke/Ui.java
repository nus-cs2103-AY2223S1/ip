package duke;

import duke.task.Task;

/**
 * The Ui class handles most of the messages shown to the user.
 */
public class Ui {
    /**
     * Constructor for Ui.
     */
    public Ui() {

    }

    /**
     * Returns message shown when user stops using Duke.
     * @return message shown when user stops using Duke.
     */
    public static String endMessage() {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Returns message shown when there is no command to undo.
     * @return message shown when there is no command to undo.
     */
    public static String noUndoMessage() {
        return ("There is no command to undo.");
    }

    /**
     * Returns message shown when a message is undone.
     * @return message shown when a message is undone.
     */
    public static String undoMessage() {
        return ("The previous command has been undone.");
    }

    /**
     * Returns message shown when a task is deleted.
     * @param task task to be deleted.
     * @return message shown when a task is deleted.
     */
    public static String deleteTask(Task task) {
        return ("Noted. I've removed this task:" + "\n" + "\t" + task.toString());
    }

    /**
     * Returns message shown when a task is marked.
     * @param task Task to be marked.
     * @return message shown when a task is marked.
     */
    public static String markTask(Task task) {
        return ("Nice! I've marked this task as done:" + "\n" + "\t" + task.toString());
    }

    /**
     * Returns message shown when a task is unmarked.
     * @param task Task to be unmarked.
     * @return message shown when a task is unmarked.
     */
    public static String unmarkTask(Task task) {
        return ("Ok, I've marked this task as not done yet:" + "\n" + "\t" + task.toString());
    }

    /**
     * Returns message shown when a task is added.
     * @param task Task to be added.
     * @return message shown when a task is added.
     */
    public static String addTask(Task task) {
        return ("Got it. I've added this task:" + "\n\t" + task.toString());
    }

    /**
     * Returns the String representing the Tasks to be printed.
     * @param list
     * @return String representing Tasks to be printed.
     */
    public static String printList(TaskList list) {
        StringBuilder print = new StringBuilder("Here are the tasks in your list:\n");
        print.append(list.toString());
        return print.toString();
    }
}
