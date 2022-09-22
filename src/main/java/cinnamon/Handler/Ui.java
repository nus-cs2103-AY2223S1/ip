package cinnamon.Handler;
import cinnamon.Storage.Storage;
import cinnamon.Tasks.Task;
import cinnamon.Tasks.TaskList;

import java.util.ArrayList;

/**
 * Class that deals with user interactions
 */
public class Ui {

    private Storage storage;

    public Ui() {
    }

    /**
     *  Prints greeting message
     */
    public void greet() {
        System.out.println("Hello!, I'm Yiye.\nWhat can I do for you? ◠‿◠");
    }


    /**
     * Prints when input is empty
     *
     * @return bye message
     */
    public String bye() {
        return "Bye! Hope to see you again soon!";
    }



    /**
     * prints list of tasks
     *
     * @param list that contains all tasks
     * @return a string of tasks
     */
    public String listTask(TaskList taskList) {
        ArrayList<Task> list = taskList.listTasks();
        if (list.isEmpty()) {
            return "There are no tasks in your taskList ~";
        }
        String t = "Here are the tasks in your taskList:";
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            s.append("").append(index).append(".").append(list.get(i).toString()).append('\n');
        }
        return t + "\n" + s;
    }

    /**
     * Prints task in new toString format
     *
     * @param todo
     * @return todo task in string
     */
    public String printTodo(Task todo) {
        return todo.toString();
    }

    /**
     * Prints task in new toString format
     *
     * @param event
     * @return event in string format
     */
    public String printEvent(Task event) {
        return event.toString();
    }

    /**
     * Prints task in new toString format
     *
     * @param dl
     * @return deadline task in string
     */
    public String printDeadline(Task dl) {
        return dl.toString();
    }

    /**
     * Print error message when file fails to load
     */
    public void loadingError() {
        System.out.println("File failed to load!");
    }

    /**
     * prints number of tasks in list
     *
     * @param i is the number of tasks
     */
    public void printSummary(int i) {
        if (i>1) {
            System.out.printf("Now you have %d tasks in your list.\n", i);
        } else {
            System.out.printf("Now you have %d task in your list.\n", i);
        }
    }

    /**
     * Prints when no matching tasks to the inputted date
     * @return message when there is no tasks matched to the date
     */
    public String dateNotFound() {
        return "No tasks on this date, check you format! --> yyyy-mm-dd";
    }

    /**
     * Prints when no matching tasks to the inputted name
     * @return message when there is no tasks matched to the name
     */
    public String nameNotFound() {
        return "No matching name of tasks";
    }

    /**
     * Prints tasks that match with the inputted date
     *
     * @param list tasks that match input date
     * @return a list of matched tasks
     */

    public String printMatchedTasks(ArrayList<Task> list) {
        String t = "Here are the matching tasks:\n";
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            s.append("").append(index).append(".").append(list.get(i).toString()).append("\n");
        }
        return t + s;
    }


    /**
     * Prints message when a task is marked done successfully
     * @return when a task is marked
     */
    public String markDoneMes() {
        return "Nice! I have marked this task as done:\n";
    }

    /**
     * Prints message when a task is marked undone successfully
     * @return when a task is unmarked
     */
    public String unmarkedMes() {
        return "This task is marked as not done:\n";
    }

    /**
     * Prints message to tell user that a task is successfully added to taskList
     */
    public String addTask() {
        return "Got it, this task is added in your list:\n";
    }

    /**
     * Prints message to tell user that the selected task is being deleted successfully
     * @return message when a task is deleted
     */
    public String deleteTask() {
        return "Noted. I've deleted this task:\n";
    }


    /**
     * @return message when a task is untagged
     */
    public String removeTag() {
        return "Tag is removed.\n";
    }
}