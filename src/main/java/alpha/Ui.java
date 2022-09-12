package alpha;

import java.util.Scanner;

/**
 * Displays messages.
 */
public class Ui {

    /** Ansi code to add white font colour to print statements */
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String ansiWhite = "\u001B[37m";

    /** Ansi code to add red font colour to print statements */
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String ansiRed = "\u001B[31m";

    /** Ansi code to add bold blue font colour to print statements */
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String ansiBlueBold = "\033[1;36m";

    /** Ansi code to add blue font colour to print statements */
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String ansiBlue = "\u001B[36m";

    /** Ansi code to reset default font colour in print statements */
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String ansiReset = "\u001B[0m";

    /**
     * Prints the message to be displayed at the start of the execution.
     */
    public void welcomeMessage() {
        System.out.println("\n------------------\n" + ansiBlueBold
                + "HELLO, I AM ALPHA!" + ansiReset + "\n------------------");
        System.out.println(ansiBlue + "What can I do for you?" + ansiReset);
        System.out.println(ansiWhite + "(enter help to learn about the commands)" + ansiReset);

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
     * @param ansiCode ANSI_CODE required.
     * @return ANSI_CODE.
     */
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    public String getAnsiCode(String ansiCode) {
        switch (ansiCode) {
        case ("ANSI_WHITE"):
            return ansiWhite;
        case ("ANSI_RED"):
            return ansiRed;
        case ("ANSI_BLUE"):
            return ansiBlue;
        case ("ANS-_BLUE_BOLD"):
            return ansiBlueBold;
        default:
            return ansiReset;
        }
    }

    /**
     * Returns text of the required colour.
     *
     * @param text Text to be printed.
     */
    public String returnText(String text) {
        return (text);
    }

}
