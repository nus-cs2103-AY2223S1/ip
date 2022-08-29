package seedu.duke.operations;

import seedu.duke.task.Task;

import java.util.Scanner;

public class Ui {
    private final Scanner cmdReader;

    public Ui() {
        this.cmdReader = new Scanner(System.in);
    }

    public String readCommand() {
        return cmdReader.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
    }

    public void showLine() {
        System.out.println("_".repeat(100));
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showNoTask() {
        System.out.println("It appears you have no tasks right now,\nwould you like to add some?");
    }

    public void showNewTask() {
        System.out.println("Got it. I've added this task:");
    }

    public void showMarked() {
        System.out.println("Nice! I've marked this task as done:");
    }

    public void showUnmarked() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    public void showAlreadyMarked() {
        System.out.println("This task is already marked:");
    }

    public void showAlreadyUnmarked() {
        System.out.println("This task is already unmarked:");
    }

    public void showRemoveTask(Task task) {
        System.out.println("Noted. I've removed this task:" + task.toString());
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public String getInvalidInputMessage() {
        return "The input is invalid, please try again.";
    }

    public String noIndexProvdedErrorMessage() {
        return "Please provide the index of he relevant task after the\ncommand.";
    }

    public String getTaskListIndexErrorMessage() {
        return "It appears there is no such task,\nPlease try again";
    }

    public String getNoDescriptionErrorMessage() {
        return "The description of the task cannot be empty.";
    }

    public String getNoTimeErrorMessage() {
        return "Please provide the relevant time for this type of task,\n"
                + "by typing \"/\" followed by the time.";
    }

    public String getInvalidTimeFormatErrorMessage() {
        return "Invalid date provided.\nPlease format the date in YYYY-MM-DD";
    }

    public void showLoadingError() {
        System.out.println("There appears to be an issue retrieving your previous records");
    }
}
