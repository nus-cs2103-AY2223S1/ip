package duke;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "_______" ;

    private Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    public static void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What may I do for you?");
    }

    public String readCommand() {
        return input.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        input.close();
    }

    public void showLoadingError() {
        System.out.println("No saved data found.");
    }

    public void showError(String message) {
        System.out.println(message);
    }
}

