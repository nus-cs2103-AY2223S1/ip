package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    /**
     * Gets a line of user input.
     * @return The user input.
     */
    public String getLine() {
        return sc.nextLine();
    }

    /**
     * Displays a divider.
     */
    public void showDivider() {
        System.out.println("    __________________________________________________");
    }

    /**
     * Display some output to the user.
     * @param response The output to be shown.
     */
    public void respond(String response) {
        showDivider();
        System.out.println("    " + response.replace("\n", "\n    "));
        showDivider();
    }

    /**
     * Creates a new UI handler.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }
}
