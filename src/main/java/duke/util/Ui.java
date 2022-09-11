package duke.util;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Program that manages Duke's communications with the user. Ui stands for User Interactions.
 *
 * @author Nephelite
 * @version 0.3
 */
public class Ui {
    /**
     * Duke logo
     */
    private static final String LOGO =
            " ____         _\n"
                    + "|  _  \\ _    _| | _____\n"
                    + "| |   | | |   | | |/  / _ \\\n"
                    + "| |__| | |__| |    <  __/\n"
                    + "|____/ \\___|_|\\_\\___|";
    /**
     * First line of Duke's greeting
     */
    private static final String GREETING_NAME = "It's your shooting star, your diamond in the rough, "
            + "idol VTuber Hoshimachi Suisei!";
    /**
     * Second line of Duke's greeting
     */
    private static final String GREETING_QUESTION = "What are you doing today?";
    /**
     * Duke begins to read a task list
     */
    private static final String BEGIN_LIST = "Here are the current tasks in your list:";
    /**
     * Duke begins to read a filtered task list
     */
    private static final String BEGIN_FILTERED_LIST = "Here are the matching tasks in your list:";
    /**
     * Duke begins to mark a task
     */
    private static final String BEGIN_MARK_TASK = "Yay! You completed this task:\n";
    /**
     * Duke begins to unmark a task
     */
    private static final String BEGIN_UNMARK_TASK = "Alright, I will mark this task as undone:\n";
    /**
     * Duke begins to mark a task
     */
    private static final String PRAISE = "Good job!";
    /**
     * Duke begins to add a task
     */
    private static final String BEGIN_ADD_TASK = "I am adding your task to the list:" + "\n";
    /**
     * Duke informs user how many tasks remain
     */
    private static final String REMAINING_TASK = " tasks remain in your list.";
    /**
     * Duke begins removing a task from the list
     */
    private static final String BEGIN_REMOVE_TASK = "Yes. I shall purge this task:\n";
    /**
     * Duke tags a task
     */
    private static final String TAG_TASK = " has been tagged with #";
    /**
     * Duke says goodbye
     */
    private static final String FAREWELL = "See you again, have a nice day!\nOtsumachi!";

    /**
     * Greets the user
     *
     * @return Duke's greetings
     * @since 0.2
     */
    public String greet() {
        return LOGO + "\n" + GREETING_NAME + "\n" + GREETING_QUESTION;
    }

    /**
     * Begin to list tasks
     *
     * @return Duke's response to reading a list
     * @since 0.3
     */
    public String list() {
        return BEGIN_LIST;
    }

    /**
     * Begin to list filtered tasks
     *
     * @return Duke's response to reading a filtered list
     * @since 0.3
     */
    public String filteredList() {
        return BEGIN_FILTERED_LIST;
    }

    /**
     * Informs the user that the task has been marked
     *
     * @param task the marked task
     * @return Duke's response to marking the task
     * @since 0.2
     */
    public String mark(Task task) {
        return BEGIN_MARK_TASK + task + PRAISE;
    }

    /**
     * Informs the user that the task has been unmarked
     *
     * @param task the unmarked task
     * @return Duke's response to unmarking the task
     * @since 0.2
     */
    public String unmark(Task task) {
        return BEGIN_UNMARK_TASK + task;
    }

    /**
     * Apologizes to the user because Duke does not understand the command
     *
     * @param exception the apology
     * @return DukeException in String form
     * @since 0.2
     */
    public String showDukeException(String exception) {
        return exception;
    }

    /**
     * Informs the user that a task has been added
     *
     * @param task Task to add
     * @param size size of taskList that Duke is using
     * @return Duke's response to adding a task
     * @since 0.2
     */
    public String addTask(Task task, int size) {
        return BEGIN_ADD_TASK + task + "\n" + size + REMAINING_TASK;
    }

    /**
     * Informs the user that a task has been deleted
     *
     * @param task Task to delete
     * @param size size of taskList that Duke is using
     * @return Duke's response to deleting a the task
     * @since 0.2
     */
    public String delete(Task task, int size) {
        return BEGIN_REMOVE_TASK + task + "\n" + size + REMAINING_TASK;
    }

    /**
     * Reads a given list
     *
     * @param tasks ArrayList contained within TaskList
     * @return Duke's response to reading the list of tasks, and the list of tasks for Duke to recite
     * @since 0.2
     */
    public String readList(ArrayList<Task> tasks) {
        String list = list();
        for (int i = 0; i < tasks.size(); i++) {
            list += "\n" + (i + 1) + ". " + tasks.get(i);
        }
        return list;
    }

    /**
     * Reads a filtered list
     *
     * @param tasks ArrayList contained within TaskList
     * @return Duke's response to reading the list of tasks, and the list of tasks for Duke to recite
     * @since 0.2
     */
    public String readFilteredList(ArrayList<Task> tasks) {
        String list = filteredList();
        for (int i = 0; i < tasks.size(); i++) {
            list += "\n" + (i + 1) + ". " + tasks.get(i) + tasks.get(i).getTags();
        }
        return list;
    }

    /**
     * Duke informs the user that a tag was added
     *
     * @param task Task tagged
     * @param tag applied Tag
     * @return String containing Duke response
     */
    public String addTag(Task task, String tag) {
        return task + TAG_TASK + tag + ".";
    }

    /**
     * Duke says goodbye to the user
     * @return String containing Duke's gooddbye
     */
    public String goodbye() {
        return FAREWELL;
    }
}
