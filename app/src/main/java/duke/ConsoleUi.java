package duke;

import java.util.Scanner;

public class ConsoleUi extends Ui {
    private Scanner sc;

    /**
     * Creates a new console-based UI handler.
     */
    public ConsoleUi() {
        sc = new Scanner(System.in);
    }

    @Override
    public String getLine() {
        return sc.nextLine();
    }

    /**
     * Displays a divider.
     */
    private void showDivider() {
        System.out.println("    __________________________________________________");
    }

    @Override
    public void respond(String response) {
        showDivider();
        System.out.println("    " + response.replace("\n", "\n    "));
        showDivider();
    }
}
