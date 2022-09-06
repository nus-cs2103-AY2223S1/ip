package duke.main;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private Scanner sc;
    private MainWindow mainWindow;

    Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints a greeting message when the bot is opened.
     */
    public void greetingMessage() {
        String greeting = "Hello! I'm Duke\n\t"
                + "What can I do for you?";
        this.outputMessage(greeting);
    }

    public void setMainWindow(MainWindow window) {
        this.mainWindow = window;
    }

    public void outputMessage(String message) {
        mainWindow.addDukeDialog(message);
    }

    /**
     * Prints an exit message.
     */
    public void exitMessage() {
        String exit = "Bye. Hope to see you again soon!";
        this.outputMessage(exit);
    }

    public void showLoadingError() {
        this.outputMessage("An error occurred when loading the file :(");
    }

    public void showError(String message) {
        this.outputMessage(message);
    }

    /**
     * Reads user command.
     *
     * @return command entered.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Lists all the tasks stored.
     *
     * @param tasks all tasks in the list
     */
    public void listTasks(ArrayList<Task> tasks) {
        StringBuilder output = new StringBuilder();
        output.append("\tHere are the tasks in your list:" + "\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append("\t" + (i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        this.outputMessage(output.toString());
    }

    /**
     * Lists all the tasks found.
     *
     * @param tasks all tasks found in the list
     */
    public void listFoundTasks(ArrayList<Task> tasks) {
        StringBuilder output = new StringBuilder();
        output.append("\tHere are the matching tasks in your list:" + "\n");
        for (int i = 0; i < tasks.size(); i++) {
            output.append("\t" + (i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        this.outputMessage(output.toString());
    }
}
