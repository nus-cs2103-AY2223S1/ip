package duke.ui;

import duke.task.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private final Scanner input = new Scanner(System.in);

    public Ui() {
    }

    public void showWelcome() {
        String logo = """
                 ____        _       \s
                |  _ \\ _   _| | _____\s
                | | | | | | | |/ / _ \\
                | |_| | |_| |   <  __/
                |____/ \\__,_|_|\\_\\___|
                """;
        System.out.println("Hello from\n" + logo);
    }

    public void showPrompt() {
        System.out.print("> ");
    }

    public String readCommand() {
        return input.nextLine();
    }

    public void showError(String errorMessage) {
        System.out.println("\nERROR: " + errorMessage + "\n");
    }

    public void showLoadingError() {
        showError("Error loading contents of data file");
    }

    public void showMessage(String s) {
        System.out.println("\n" + s + "\n");
    }

    public void showList(ArrayList<Task> list) {
        System.out.println();
        for (Task task : list) {
            System.out.println(task);
        }
        System.out.println();
    }
}
