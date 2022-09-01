package alpha;

import java.util.Scanner;

public class Ui {

    /** Ansi code to add white font colour to print statements */
    public final String ANSI_WHITE = "\u001B[37m";

    /** Ansi code to add red font colour to print statements */
    public final String ANSI_RED = "\u001B[31m";

    /** Ansi code to add bold blue font colour to print statements */
    public final String ANSI_BLUE_BOLD = "\033[1;36m";

    /** Ansi code to add blue font colour to print statements */
    public final String ANSI_BLUE = "\u001B[36m";

    /** Ansi code to reset default font colour in print statements */
    public final String ANSI_RESET = "\u001B[0m";

    /**
     * Prints the message to be displayed at the start of the execution.
     */
    public void welcomeMessage() {
        System.out.println("\n------------------\n" + ANSI_BLUE_BOLD +
                "HELLO, I AM ALPHA!" + ANSI_RESET + "\n------------------");
        System.out.println(ANSI_BLUE + "What can I do for you?" + ANSI_RESET);
        System.out.println(ANSI_WHITE + "(enter help to learn about the commands)" + ANSI_RESET);

    }

    /**
     * Takes and returns inputs from the user.
     *
     * @param in Scanner object to take inputs from the user.
     * @return User input.
     */
    public String takeUserInput(Scanner in) {
        String input = in.nextLine();
        return input;
    }

    /**
     * Returns the required ANSI_CODE.
     *
     * @param ANSI_CODE ANSI_CODE required.
     * @return ANSI_CODE.
     */
    public String getANSI_CODE(String ANSI_CODE) {
        switch (ANSI_CODE) {
            case ("ANSI_WHITE"):
                return ANSI_WHITE;
            case ("ANSI_RED"):
                return ANSI_RED;
            case ("ANSI_BLUE"):
                return ANSI_BLUE;
            case ("ANS-_BLUE_BOLD"):
                return ANSI_BLUE_BOLD;
            default:
                return ANSI_RESET;
        }
    }

    /**
     * Prints text of the required colour.
     *
     * @param colour Required font colour of the print statements.
     * @param text Text to be printed.
     */
    public void colouredPrint(String colour, String text) {
        System.out.println(colour + text + ANSI_RESET);
    }

}
