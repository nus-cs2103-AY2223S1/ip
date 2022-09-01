package alpha;

import java.util.Scanner;

public class Ui {
    public final String ANSI_YELLOW = "\u001B[33m";

    public final String ANSI_WHITE = "\u001B[37m";
    public final String ANSI_RED = "\u001B[31m";

    public final String ANSI_BLUE_BOLD = "\033[1;36m";
    public final String ANSI_BLUE = "\u001B[36m";
    public final String ANSI_RESET = "\u001B[0m";
    public void welcomeMessage() {
        System.out.println("\n------------------\n" + ANSI_BLUE_BOLD + "HELLO, I AM ALPHA!" + ANSI_RESET + "\n------------------");
        System.out.println(ANSI_BLUE + "What can I do for you?" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "(enter help to learn about the commands)" + ANSI_RESET);

    }
    public String takeUserInput(Scanner in) {
        String input = in.nextLine();
        return input;
    }
    public void colouredPrint(String colour, String text) {
        System.out.println(colour + text + ANSI_RESET);
    }

}
