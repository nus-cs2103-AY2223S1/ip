package duke;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Creates a Ui object.
 */
public class Ui {

    private static final String LINE_DIVIDER = "____________________________________________________________";

    public static String messageWithLine(String message) {
        return LINE_DIVIDER + "\n" + message + "\n" + LINE_DIVIDER;
    }
    /**
     * Prints intro interface.
     */
    public String printIntro() {
        return "Wassup la I'm Duke\nWhat you want?";
    }

    /**
     * Prints goodbye message.
     */
    public String printGoodByeMessage() {
        return "Bye. Zai Jian!";
    }

    /**
     * Prints task list.
     * @param tl TaskList to be printed
     */
    public String printList(TaskList tl) {
        StringBuilder message = new StringBuilder();
        if (tl.size() == 0) {
            message.append("List is empty la");
            return message.toString();
        }
        message.append("Here are your tasks la:");
        for (int j = 0; j < tl.size(); j++) {
            message.append("\n").append(j + 1).append(".").append(tl.get(j).toString());
        }
        return message.toString();
    }

    /**
     * Ui for marking tasks.
     * @param task to be marked
     */
    public String printMarkedMsg(Task task) {
        return "Ok ticked this already\n" + task.toString();
    }

    /**
     * Ui for unmarking tasks.
     * @param task to be unmarked
     */
    public String printUnmarkedMsg(Task task) {
        return "Ok not done yet ah\n" + task.toString();
    }

    /**
     * Ui for deleting tasks.
     * @param removedTask String of removed task
     * @param size of TaskList
     */
    public String printDeleteMsg(String removedTask, int size) {
        return "I remove this ah:\n" + removedTask + "\nNow " + size + " tasks only";
    }

    /**
     * Ui for index inputs that are out of bounds.
     */
    public String printOutOfBoundsMsg() {
        return "Out of bounds lah, try again\n";
    }

    /**
     * Ui for no task input errors.
     */
    public String printNoTaskInputMsg() {
        return "☹ OOPS!!! Why empty";
    }

    /**
     * Ui for successful task additions.
     * @param task that was added
     * @param size of TaskList
     */
    public String printTaskAddedMsg(Task task, int size) {
        return "Ok I add your task already:\n" + task + "\nNow " + size + " tasks already";
    }

    /**
     * Ui for general errors.
     */
    public String printError() {
        return "☹ Walao what do you mean";
    }

    /**
     * Ui for clearing whole TaskList.
     */
    public String printClearMsg() {
        return "CLEAR ALL LIAO";
    }

    /**
     * Returns the message for filtered list.
     * @param tl Filtered TaskList.
     * @return message to be printed.
     */
    public String printFilteredList(TaskList tl) {
        if (tl.size() == 0) {
            return "List is empty la";
        }
        StringBuilder message = new StringBuilder("Matching one:");
        for (int j = 0; j < tl.size(); j++) {
            message.append("\n").append(j + 1).append(":").append(tl.get(j).toString());
        }
        return message.toString();
    }

    public String printIncompleteEvent() {
        return "Why not complete event?";
    }

    public String printIncompleteDeadline() {
        return "Why not complete deadline?";
    }
}
