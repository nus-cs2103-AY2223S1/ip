package UI;

import java.util.Scanner;

public class Ui {

    private static final String LINE = "   ___________________________";

    private Scanner sc = new Scanner(System.in);

    public String readInput() {
        String input;
        input = sc.nextLine().strip();
        return input;

    }

    public void showLoadingError() {
        System.out.println(LINE);

        System.out.println("Loading Errors");
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        System.out.println("Show Welcome");
    }

}
