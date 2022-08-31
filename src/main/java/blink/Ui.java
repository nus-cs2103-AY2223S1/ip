package blink;

import blink.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui that control logic of what is displayed on screen.
 */
public class Ui {

    private static final String SPACING = "--------------------------------------";
    private Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays welcome message to user when Blink program starts.
     */
    public void showWelcome() {
        System.out.println(Ui.SPACING + "\n"
                + "Hello! Blink.Blink here\n"
                + "What can I do for you today?\n"
                + Ui.SPACING);
    }

    /**
     * Display goodbye message when Blink program ends.
     */
    public String showBye() {
        return "Bye bye~ Glad to be of service :D";
    }

    /**
     * Displays the SPACING constant between lines.
     */
    public void showLine() {
        System.out.println(Ui.SPACING);
    }

    /**
     * Shows the user input error found.
     *
     * @param errMessage Error message of exceptions caught
     */
    public void showError(String errMessage) {
        System.out.println("Error found: " + errMessage);
    }

    /**
     * Reads the next line of user input.
     *
     * @return Next time of user input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Display all the Tasks in TaskList.
     *
     * @param tasks TaskList which tasks are to be displayed.
     */
    public String showList(TaskList tasks) {
        return tasks.listTask();
    }

    /**
     * Display the task to be marked.
     *
     * @param tasks TaskList of all the current Tasks
     * @param num Number position of Task to mark
     */
    public String mark(TaskList tasks, int num) {
        Task task = tasks.get(num - 1);
        return "This task has been marked as done\n" + task;
    }

    /**
     * Display task to be unmarked.
     *
     * @param tasks TaskList of all the current Tasks
     * @param num Number position of Task to unmark
     */
    public String unMark(TaskList tasks, int num) {
        Task task = tasks.get(num - 1);
        return "This task has been marked as done\n" + task;
    }

    /**
     * Display information of Task deleted and TaskList after deletion.
     *
     * @param tasks TaskList where the task is deleted from
     * @param task Task that is deleted
     */
    public String deleteTask(TaskList tasks, Task task) {
        return "Blink.Task has been deleted successfully.\n" + task
               + "\n" + tasks.deleted();
    }

    /**
     * Displays all Tasks in TaskList that matches date specified.
     *
     * @param tasks TaskList to check task for date specified
     * @param date Date to filter by, to get all Tasks of this date
     */
    public String showFilter(ArrayList<Task> tasks, LocalDate date) {
        String output = "";
        if (tasks.size() == 0) {
            return "No task found on " + date.toString();
        } else {
            String currentSize = tasks.size() + ((tasks.size() == 1)? " task": " tasks")
                    + " found";
            output += currentSize;
            for (int x = 0; x < tasks.size(); x++) {
                String taskInfo = "\n" + tasks.get(x);
                output += taskInfo;
            }
        }
        return output;
    }

    /**
     * Display the task added to TaskList and its information.
     * @param tasks TaskList where the task is added to
     * @param task Task which has just been added
     */
    public String showAddTask(TaskList tasks, Task task) {
        return "Alright, this task has been successfully added!\n"
                + task + "\nTotal of " + tasks.length() + " tasks now";
    }

    /**
     * Displays all the Tasks with the keyword specified in TaskList.
     *
     * @param tasks ArrayList containing all the tasks with specified keyword
     * @param keyword Keyword to filter all the Tasks by
     */
    public String showFind(ArrayList<Task> tasks, String keyword) {
        String output = "";
        if (tasks.size() == 0) {
            return "No tasks found with keyword: " + keyword;
        } else {
            String currentSize = tasks.size() + ((tasks.size() == 1)? " task": " tasks")
                    + " found";
            output += currentSize;
            for (int x = 0; x < tasks.size(); x++) {
                String taskInfo = "\n" + tasks.get(x);
                output += taskInfo;
            }
        }
        return output;
    }
}
