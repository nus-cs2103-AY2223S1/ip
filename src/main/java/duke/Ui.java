package duke;

import java.util.Scanner;

/**
 * Interacts with the console of Duke users, displaying operations, status, and errors.
 */
public class Ui {

    /**
     * Displays welcome message to users.
     */
    protected void greet() {
        System.out.println("Good Day! ~ Merlin at your service ~");
    }

    protected String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Displays to the user that there are no outstanding tasks.
     */
    protected void showLoadingError() {
        System.out.println("No previous records detected, creating new file...");
    }

    /**
     * Displays farewell message to users.
     */
    protected String outro() {
        return "Have a nice day ~ Merlin out ~";
    }

    protected String updateTask(Task updatedTask, String status) {
        return String.format("Marked task as %s.\n%s\n", status, updatedTask);
    }

    /**
     * Displays a given String to users.
     * @param output Message to be shown.
     */
    protected void display(String output){
        System.out.println(output);
    }

    protected String addTaskConfirmation(Task task, int size) {
        return String.format("Got it. I've added this task:\t%s\nNow you have %d tasks in your list.", task, size);
    }

    protected String deleteTaskConfirmation(Task task, int size) {
        return String.format("Noted. I've removed this task:\t%s\nNow you have %d tasks in the list.\n", task, size);
    }
}
