package Duke;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that deals with user interactions
 */
public class Ui {

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


}