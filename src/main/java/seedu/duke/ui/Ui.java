package seedu.duke.ui;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.util.Scanner;
public class Ui {
    boolean loadingError;
    public Ui() {
        this.loadingError = false;
    }
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm seedu.duke.Duke\n"
                + "What can I do for you?\n");
    }

    public void showLoadingError() {
        System.out.println("----- Loading Error!! -----\n");
    }

    public void showError(String errorMessage) {
        System.out.println(Style.INDENTATION + errorMessage + "\n");
    }
    public String readCommand() {
        Scanner myObj = new Scanner(System.in);
        String command = myObj.nextLine();

        return command;
    }

    public void showLine() {
        System.out.println("__________________________________________"
                + "__________________________________________" + "\n");
    }

    public void showGoodbye() {
        System.out.println(Style.INDENTATION + "Bye. Hope to see you again soon!\n");
    }

    public void showList(TaskList tasks) {
        System.out.println(Style.INDENTATION + "Here are the tasks in your list:");
        tasks.listTasks();
        System.out.println("");
    }

    public void showMarkStatus(Task task) {
        System.out.println(Style.INDENTATION + "Nice! I've marked this seedu.duke.task as done:");
        System.out.println(Style.INDENTATION + Style.HALF_INDENTATION + task + "\n");
    }

    public void showUnmarkStatus(Task task) {
        System.out.println(Style.INDENTATION + "OK, I've marked this seedu.duke.task as not done yet:");
        System.out.println(Style.INDENTATION + Style.HALF_INDENTATION + task + "\n");
    }

    public void addTask(Task task) {
        System.out.println(Style.INDENTATION + "Got it. I've added this seedu.duke.task:");
        System.out.println(Style.INDENTATION + Style.HALF_INDENTATION + task);

    }

    public void deleteTask(Task task) {
        System.out.println(Style.INDENTATION + "Noted. I've removed this seedu.duke.task:");
        System.out.println(Style.INDENTATION + Style.HALF_INDENTATION + task);
    }

    public void displayNumberOfTasks(int numberOfTasks) {
        String taskOrTasks = numberOfTasks == 1 ? "task" : "tasks";
        System.out.println(Style.INDENTATION + "Now you have " + numberOfTasks + " "
                + taskOrTasks + " in the list.\n");
    }
    public void displayMatchingTasks() {
        System.out.println(Style.INDENTATION + "Here are the matching tasks in your list:");
    }
}
