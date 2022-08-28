package duke;

import java.util.Scanner;

public class Ui {
    private final Scanner cmdReader;

    Ui() {
        this.cmdReader = new Scanner(System.in);
    }

    String readCommand() {
        return cmdReader.nextLine();
    }
    void showWelcome() {
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
    }

    void showLine() {
        System.out.println("_".repeat(100));
    }

    void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    void showNoTask() {
        System.out.println("It appears you have no tasks right now,\nwould you like to add some?");
    }
    void showNewTask() {
        System.out.println("Got it. I've added this task:");
    }
    void showMarked() {
        System.out.println("Nice! I've marked this task as done:");
    }
    void showUnmarked() {
        System.out.println("OK, I've marked this task as not done yet:");
    }
    void showAlreadyMarked() {
        System.out.println("This task is already marked:");
    }
    void showAlreadyUnmarked() {
        System.out.println("This task is already unmarked:");
    }
    void showRemoveTask(Task task) {
        System.out.println("Noted. I've removed this task:" + task.toString());
    }

    void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
    String getInvalidInputMessage() {
        return "The input is invalid, please try again.";
    }
    String noIndexProvdedErrorMessage() {
        return "Please provide the index of he relevant task after the\ncommand.";
    }

    String getTaskListIndexErrorMessage() {
        return "It appears there is no such task,\nPlease try again";
    }
    String getNoDescriptionErrorMessage() {
        return "The description of the task cannot be empty.";
    }
    String getNoTimeErrorMessage() {
        return "Please provide the relevant time for this type of task,\n"
                + "by typing \"/\" followed by the time.";
    }
    String getInvalidTimeFormatErrorMessage() {
        return "Invalid date provided.\nPlease format the date in YYYY-MM-DD";
    }
    void showLoadingError() {
        System.out.println("There appears to be an issue retrieving your previous records");
    }
}
