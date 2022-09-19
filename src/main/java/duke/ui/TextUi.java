package duke.ui;

import java.util.Scanner;

public class TextUi implements Ui {
    private static final String DIVIDER = "____________________________________________________________\n";

    private Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        showLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "\nWhat can I do for you?";
        System.out.println("Hello from\n" + logo);
        showLine();
    }

    private void showLine() {
        System.out.println(DIVIDER);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void sendMessage(String text) {
        showLine();
        System.out.println(text);
        showLine();
    }

    public void showError(String text) {
        showLine();
        System.out.println(text);
        showLine();
    }
}
