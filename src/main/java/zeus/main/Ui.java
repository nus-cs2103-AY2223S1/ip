package zeus.main;

import zeus.task.Task;

import java.util.ArrayList;
import java.util.Scanner;



/**
 * Class that deals with interactions with the user
 */
public class Ui {

    private Scanner scanner;

    /**
     * Constructor of Ui class.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        showWelcome();
    }

    /**
     * Print message to indicated task is added and size of task list.
     *
     * @param task Task to be added
     * @param size Size of list after task is added
     */
    public void printAddTask(Task task, int size) {
        generateLine();
        printFormatted("Got it. I've added this task:");
        printFormatted("  " + task);
        printFormatted("Now you have " + size + " tasks in the list.");
        generateLine();
    }

    /**
     * Print message to indicated task is deleted and size of task list.
     *
     * @param task Task to be deleted
     * @param size Size of list after task is deleted
     */
    public void printDeleteTask(Task task, int size) {
        generateLine();
        printFormatted("Noted. I've removed this task:");
        printFormatted("  " + task);
        printFormatted("Now you have " + size + " tasks in the list.");
        generateLine();
    }

    /**
     * Prints the tasks in the task list.
     *
     * @param taskList ArrayList containing tasks.
     */
    public void printList(ArrayList<Task> taskList) {
        generateLine();
        printFormatted("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            String currLine = "\t " + (i + 1) + "." + t;
            System.out.println(currLine);
        }
        generateLine();
    }

    /**
     * Prints text to welcome user.
     */
    public void showWelcome() {
        generateLine();
        printFormatted("Hello! I'm Zeus");
        printFormatted("What can I do for you?");
        generateLine();
    }

    /**
     * Prints exit message for user.
     */
    public void exitMessage() {
        generateLine();
        printFormatted("Bye. Hope to see you again soon!");
        generateLine();
    }

    /**
     * Prints line.
     */
    public void generateLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Adds tab before message
     *
     * @param message String message to format
     */
    public void printFormatted(String message) {
        System.out.println("\t " + message);
    }

    /**
     * Reads line using Scanner.
     *
     * @return String representing the line read
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Print error message formatted.
     *
     * @param message Error message to be printed
     */
    public void showError(String message) {
        generateLine();
        printFormatted(message);
        generateLine();
    }

    /**
     * Print error message for file loading errors.
     */
    public void showLoadingError() {
        generateLine();
        System.out.println("Error loading file.");
        generateLine();
    }

}
