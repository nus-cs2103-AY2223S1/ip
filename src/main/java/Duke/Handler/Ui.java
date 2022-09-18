package Duke.Handler;
import Duke.Storage.Storage;
import Duke.Tasks.Task;
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
     */
    public void bye() {
        System.out.println("Bye! Hope to see you again soon!");
    }



    /**
     * prints list of tasks
     *
     * @param list that contains all tasks
     */
    public void listTask(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, list.get(i).toString());
        }
    }

    /**
     * Prints task in new toString format
     *
     * @param todo
     */
    public void printTodo(Task todo) {
        System.out.println(todo.toString());
    }

    /**
     * Prints task in new toString format
     *
     * @param event
     */
    public void printEvent(Task event) {
        System.out.println(event.toString());
    }

    /**
     * Prints task in new toString format
     *
     * @param dl
     */
    public void printDeadline(Task dl) {
        System.out.println(dl.toString());
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
     */
    public void notFound() {
        System.out.println("No tasks on this date, check you format! --> MMM(eg. Apr) dd yyy");
    }

    /**
     * Prints when no matching tasks to the inputted name
     */
    public void nameNotFound() {
        System.out.println("No matching name of tasks");
    }

    /**
     * Prints tasks that match with the inputted date
     *
     * @param list tasks that match input date
     */

    public void printMatchedTasks(ArrayList<Task> list) {
        System.out.println("Here are the matching tasks:");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, list.get(i).toString());
        }
    }


    /**
     * Prints message when a task is marked done successfully
     */
    public void markDoneMes() {
        System.out.println("Nice! I have marked this task as done:");
    }

    /**
     * Prints message when a task is marked undone successfully
     */
    public void unmarkedMes() {
        System.out.println("This task is marked as not done:");
    }

    /**
     * Prints message to tell user that a task is successfully added to taskList
     */
    public void addTask() {
        System.out.println("Got it, this task is added in your list:");
    }

    /**
     * Prints message to tell user that the selected task is being deleted successfully
     */
    public void deleteTask() {
        System.out.println("Noted. I've removed this task:");
    }

    public void tag() {
        System.out.println("Yay, you have successfully tagged your task!");
    }

    public void unTag() {
        System.out.println("Tag is removed.");
    }
}