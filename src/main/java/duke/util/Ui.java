package duke.util;

import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_BAR = "-------------------------";
    private static final String INDENTATION = "    ";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    private static String formatOutput(String output) {
        return HORIZONTAL_BAR + '\n' + INDENTATION + output + '\n' + HORIZONTAL_BAR + '\n' + '\n';
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void printOutput(String output) {
        System.out.println(formatOutput(output));
    }
}
