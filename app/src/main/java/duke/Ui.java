package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public String getLine() {
        showDivider();
        return sc.nextLine();
    }

    public void showDivider() {
        System.out.println("    __________________________________________________");
    }

    public void respond(String response) {
        showDivider();
        System.out.println("    " + response.replace("\n", "\n    "));
    }

    public Ui() {
        sc = new Scanner(System.in);
    }
}
