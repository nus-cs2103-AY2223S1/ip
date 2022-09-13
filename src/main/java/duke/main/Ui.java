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
        String exit = "\tBye. Hope to see you again soon!";
        this.outputMessage(exit);
    }

    public void showLoadingError() {
        this.outputMessage("\tAn error occurred when loading the file :(");
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
