package duke;

import java.util.Scanner;

public class Ui {
    private static final String SEPARATOR = "     ____________________________________________________________\n";
    private static final String INDENT = "     ";
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        dukePrint("Oi! I'm Dook",
                "What's up?",
                "Please type your date and time in this format: yyyy-mm-dd");
    }

    public void showBye() {
        dukePrint("Bye.", ">:)");
    }

    public void showLoadingError() {
        dukePrint("Failed to load :(");
    }

    public void showError(String msg) {
        dukePrint("Something bad happened! :O", "Error message: " + msg);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void close() {
        this.sc.close();
    }

    public static void dukePrint(String... lines) {
        StringBuilder res = new StringBuilder(SEPARATOR);
        for (String line : lines) {
            res.append(INDENT).append(line).append("\n");
        }
        res.append(SEPARATOR);
        System.out.println(res.toString());
    }
}
