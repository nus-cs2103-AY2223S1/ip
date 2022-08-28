package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * A Ui class to handle interactions with the user.
 */
public class Ui {

    /** The scanner that is used to read from the system input. */
    private Scanner sc;

    /** TaskList to handle all tasks related operations. */
    private TaskList taskList;

    /**
     * Constructor for a Ui object.
     *
     * @param taskList TaskList to handle all tasks related operations.
     */
    public Ui(TaskList taskList) {
        sc = new Scanner(System.in);
        this.taskList = taskList;
    }

    /**
     * Reads the next command input by the user.
     * @return The next command input by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the input exception to the command line.
     * @param e The exception to be printed.
     */
    public void showError(DukeException e) {
        System.out.println(e);
    }


    /**
     * Prints the number of current tasks, as well as how many are completed.
     */
    public void printListCount() {
        System.out.println("  You have " + this.taskList.size() + " tasks currently, "
                + Task.totalDone + " are completed");
    }

    /**
     * Prints a list of all the tasks.
     */
    public void printList() {
        if (this.taskList.size() == 0) {
            System.out.println("  List is empty!");
        } else {
            System.out.println("  List of tasks:");
            for (int i = 1; i <= this.taskList.size(); i++) {
                System.out.println("\t" + i + ": " + this.taskList.get(i - 1));
            }
            printListCount();
        }
    }

    /**
     * Prints all the tasks from the given input list.
     *
     * @param lst The list of tasks to be printed.
     */
    public void printTasks(List lst) {
        if (this.taskList.size() != 0) {
            for (int i = 1; i <= lst.size(); i++) {
                System.out.println("\t" + i + ": " + lst.get(i - 1));
            }
        }
    }

    /**
     * Prints all the tasks in the task list.
     */
    public void printTasks() {
        if (this.taskList.size() != 0) {
            for (int i = 1; i <= this.taskList.size(); i++) {
                System.out.println("\t" + i + ": " + this.taskList.get(i - 1));
            }
        }
    }

    /**
     * Prints the hello introduction to Duke.
     */
    public void startMessage() {
        String logo = " _    _ ______ _      _      ____     ______ _____   ____  __  __  "
                + "  _____  _    _ _  ________ \n"
                + "| |  | |  ____| |    | |    / __ \\   |  ____|  __ \\ / __ \\|  \\/  |  |"
                + "  __ \\| |  | | |/ /  ____|\n"
                + "| |__| | |__  | |    | |   | |  | |  | |__  | |__) | |  | | \\  / |  | |  | | |  | | ' /| |__   \n"
                + "|  __  |  __| | |    | |   | |  | |  |  __| |  _  /| |  | | |\\/| |  | |  | | |  | |  < |  __| \n"
                + "| |  | | |____| |____| |___| |__| |  | |    | | \\ \\| |__| | |  | |  | |__| | |__| | . \\| |____\n"
                + "|_|  |_|______|______|______\\____/   |_|    |_|  \\_\\\\____/|_|  |_|  |_"
                + "____/ \\____/|_|\\_\\______|\n";
        System.out.println(logo);
        System.out.println("  How may I help you today?");
        newLine();
    }

    /**
     * Prints the exit program text.
     */
    public void exitMessage() {
        System.out.println("Bye:( Hope to see you again soon!");
        newLine();
    }

    /**
     * Prints a new line.
     */
    public void newLine() {
        System.out.println("________________________________________________________________________"
                + "_______________________");
    }
}
