package blink;

import java.time.LocalDate;
import java.util.ArrayList;

import blink.task.Task;

/**
 * Ui that control logic of what is displayed on screen.
 */
public class Ui {

    /**
     * Display all the Tasks in TaskList.
     *
     * @param tasks TaskList which tasks are to be displayed.
     * @return String of all Task in TaskList
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
        Task task = tasks.getTask(num - 1);
        return "This task has been marked as done\n" + task;
    }

    /**
     * Display task to be unmarked.
     *
     * @param tasks TaskList of all the current Tasks
     * @param num Number position of Task to unmark
     */
    public String unMark(TaskList tasks, int num) {
        Task task = tasks.getTask(num - 1);
        return "This task has been unmarked\n" + task;
    }

    /**
     * Display information of Task deleted and TaskList after deletion.
     *
     * @param tasks TaskList where the task is deleted from
     * @param task Task that is deleted
     */
    public String deleteTask(TaskList tasks, Task task) {
        return "Task has been deleted successfully.\n" + task
               + "\n" + tasks.toStringAfterDelete();
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
            String currentSize = tasks.size() + ((tasks.size() == 1) ? " task" : " tasks")
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
            String str = (tasks.size() == 1)
                    ? " task"
                    : " tasks";
            String currentSize = tasks.size() + str + " found";
            output += currentSize;
            for (int x = 0; x < tasks.size(); x++) {
                String taskInfo = "\n" + tasks.get(x);
                output += taskInfo;
            }
        }
        return output;
    }

    /**
     * Displays the task that has been tagged.
     *
     * @param index Position of Task to tag in TaskList
     * @param tasks TaskList containing the task
     * @return Success message of tagging and Task that is tagged
     */
    public String showTag(int index, TaskList tasks) {
        Task task = tasks.getTask(index - 1);
        String successMsg = "Tag added successfully \n";
        return successMsg + task.toString();
    }
}
