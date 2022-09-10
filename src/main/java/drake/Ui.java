package drake;

import java.util.Scanner;

public class Ui {
    private final String DASH = "------------------------------------------------------";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(DASH);
        System.out.println("You used to call me on my cellphone");
        StringBuilder logo = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            logo.append("DRAKE ".repeat(4));
            logo.append("DRAKE");
            if (i == 4) break;
            logo.append("\n");
        }
        System.out.println(logo);
        System.out.println("!@#$%^&*()-+!@#$%^&*()`~`!@#$");
        System.out.println("drake.Drake's (me) the kind of guy to help you out uwu");
        System.out.println("Go ahead, make that hotline bling");
        System.out.println(DASH);
    }

    public String readInput() {
        return sc.nextLine().trim();
    }

    public void printLine(Object line) {
        System.out.println(line);
    }

    public void printBye() {
        System.out.println("I'm down for you always. See you " + ANSI_RED + "<3" + ANSI_RESET);
    }

    public void printDash() {
        System.out.println(DASH);
    }

    public void printError(String errorMessage) {
        System.out.println(ANSI_RED + errorMessage + ANSI_RESET);
    }
}
