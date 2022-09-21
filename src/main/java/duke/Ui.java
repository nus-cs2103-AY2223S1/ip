package duke;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Creates a Ui object.
 */
public class Ui {

    private static final String LINE_DIVIDER = "____________________________________________________________";

    /**
     * Adds line dividers to the message to be printed.
     * @param message message to be printed.
     * @return message with line dividers.
     */
    public static String messageWithLine(String message) {
        return LINE_DIVIDER + "\n" + message + "\n" + LINE_DIVIDER;
    }
    /**
     * Prints intro interface.
     * @return message to be printed.
     */
    public String printIntro() {
        return "Wassup la I'm Ah Duke\nWhat you want?";
    }

    /**
     * Prints goodbye message.
     * @return message to be printed.
     */
    public String printGoodByeMessage() {
        return "Bye. Zai Jian!";
    }

    /**
     * Prints task list.
     * @param tl TaskList to be printed
     * @return message to be printed.
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
     * @return message to be printed.
     */
    public String printMarkedMsg(Task task) {
        return "Ok ticked this already\n" + task.toString();
    }

    /**
     * Ui for unmarking tasks.
     * @param task to be unmarked
     * @return message to be printed.
     */
    public String printUnmarkedMsg(Task task) {
        return "Ok not done yet ah\n" + task.toString();
    }

    /**
     * Ui for deleting tasks.
     * @param removedTask String of removed task
     * @param size of TaskList
     * @return message to be printed.
     */
    public String printDeleteMsg(String removedTask, int size) {
        return "I remove this ah:\n" + removedTask + "\nNow " + size + " tasks only";
    }

    /**
     * Ui for index inputs that are out of bounds.
     * @return Out of bounds message.
     */
    public String printOutOfBoundsMsg() {
        return "Out of bounds lah, try again\n";
    }

    /**
     * Ui for no task input errors.
     * @return Task input string.
     */
    public String printNoTaskInputMsg() {
        return "☹ OOPS!!! Why empty";
    }

    /**
     * Ui for successful task additions.
     * @param task that was added
     * @param size of TaskList
     * @return Task added string.
     */
    public String printTaskAddedMsg(Task task, int size) {
        return "Ok I add your task already:\n" + task + "\nNow " + size + " tasks already";
    }

    /**
     * Ui for general errors.
     * @return Error string.
     */
    public String printError() {
        return "☹ Walao what do you mean";
    }

    /**
     * Ui for clearing whole TaskList.
     * @return Clear tasklist message.
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

    /**
     * Prints the help page for Ah Duke.
     * @return Help page.
     */
    public String printHelpPage() {
        return "Welcome to Ah Duke v1.1: The chatbot that helps you keep track of your tasks in a Singaporean way."
                + "\nAh Duke will help you come:" + "\nUse 'list' to list all current lists on tasklist."
                + "\nUse 'todo TASKNAME' to save taskName as a todo task."
                + "\nUse 'deadline TASKNAME /by DD/MM/YYYY HHmm' to save a deadline with a deadline in the "
                + "format DD/MM/YYYY HHmm."
                + "\nUse 'event TASKNAME /at DD/MM/YYYY HHmm' to save an event task in the format"
                + "with a date time format of DD/MM/YYYY HHmm."
                + "\nUse 'mark INDEX' or 'unmark INDEX' to mark the task at that index as done or not done."
                + "\nUse 'find WORD' to find the matching words of tasks."
                + "\nUse 'clear' to clear all tasks.";
    }

    /**
     * Ui for incomplete events.
     * @return message to be printed.
     */
    public String printIncompleteEvent() {
        return "Why not complete event?";
    }

    /**
     * Ui for incomplete deadlines.
     * @return message to be printed.
     */
    public String printIncompleteDeadline() {
        return "Why not complete deadline?";
    }
}
