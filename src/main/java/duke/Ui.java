package duke;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

/**
 * Class that deals with interactions with the user
 */
public class Ui {

    /**
     * A method that returns the output of list
     *
     * @param tl The latest tasklist
     * @return String The list
     */
    public static String getListMessage(TaskList tl) {
        String message = "";
        for (int i = 1; i < tl.count() + 1; i++) {
            message += (i) + ". " + tl.getTask(i - 1) + "\n";
        }
        return message;
    }

    /**
     * A method that returns the output of mark
     *
     * @param t The marked task
     * @return String Marked message
     */
    public static String getMarkMessage(Task t) {
        return "Nice! I've marked this task as done:\n  [X] " + t.description;
    }

    /**
     * A method that returns the output of unmark
     *
     * @param t The unmarked task
     * @return String unmarked message
     */
    public static String getUnmarkMessage(Task t) {
        return "OK, I've marked this task as not done yet:\n  [ ] " + t.description;
    }

    /**
     * A method that returns the output of todo
     *
     * @param t The todo
     * @param count The number of tasks
     * @return String todo message
     */
    public static String todoMessage(Todo t, int count) {
        return "Got it. I've added this task:\n " + t +
                "\nNow you have " + count + " tasks in the list.";
    }

    /**
     * A method that returns the output of deadline
     *
     * @param d The deadline
     * @param count The number of tasks
     * @return String deadline message
     */
    public static String deadlineMessage(Deadline d, int count) {
        return "Got it. I've added this task:\n " + d +
                "\nNow you have " + count + " tasks in the list.";
    }

    /**
     * A method that returns the output of event
     *
     * @param e The deadline
     * @param count The number of tasks
     * @return String event message
     */
    public static String eventMessage(Event e, int count) {
        return "Got it. I've added this task:\n " + e +
                "\nNow you have " + count + " tasks in the list.";
    }

    /**
     * A method that returns the output of delete
     *
     * @param t The task
     * @param count The number of tasks
     * @return String delete message
     */
    public static String deleteMessage(Task t, int count) {
        return "Noted. I've removed this task: \n " + t + "\nNow you have "
                + count + " tasks in the list.";
    }

    /**
     * A method that returns the output of find
     *
     * @param lst The list of all messages with the keyword
     * @return String find message
     */
    public static String findMessage(ArrayList<Task> lst) {
        int counter = 1;
        String tasks = "Here are the matching tasks in your list:\n";
        for (Task t: lst) {
            tasks += counter + ". " + t.toString() + "\n";
            counter++;
        }
        return tasks;
    }

    /**
     * A method that returns the output of postpone
     *
     * @param t The newly edited task
     * @return String postpone message
     */
    public static String postponeMessage(Task t) {
        return "I've changed the date of this task:\n " + t;
    }

}
