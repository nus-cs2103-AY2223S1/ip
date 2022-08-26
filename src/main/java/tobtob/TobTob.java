package tobtob;

import java.util.Scanner;

/**
 * Represent a Ui that interacts with the user
 */
public class TobTob {
    private static final String TOB_TOB_LOGO = "            __                  __           __                  __\n"
            + "           |  |____    _____   |  |_____    |  |____    _____   |  |_____\n"
            + "           |   ___/   /     \\  |        \\   |   ___/   /     \\  |        \\\n"
            + "           |  |      |  / \\  | |   / \\   |  |  |      |  / \\  | |   / \\   |\n"
            + "           |  \\_____ |  \\ /  | |   \\ /   |  |  \\_____ |  \\ /  | |   \\ /   |\n"
            + "            \\______/  \\_____/   \\_______/    \\______/  \\_____/   \\_______/";

    private static final int TOB_TOB_LUCKY_NUMBER = 88;
    private static final String TOB_TOB_BOUNDARY = new String(new char[TOB_TOB_LUCKY_NUMBER]).replace("\0", "~");
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Returns a {@link String} of the input by user.
     *
     * @return {@link String}
     */
    public String readInput() {
        return sc.nextLine().strip();
    }

    /**
     * Prints the specified {@link String} with indentation
     *
     * @param s a {@link String} to print
     */
    public void printTobTobIndent(String s) {
        System.out.print("\t");
        System.out.println(s.replace("\n", "\n\t"));
    }

    /**
     * Prints the boundary
     */
    public void printTobTobBoundary() {
        printTobTobIndent(TOB_TOB_BOUNDARY);
    }

    /**
     * Greets the user
     */
    public void tobTobGreets() {
        printTobTobIndent(TOB_TOB_LOGO);
        printTobTobIndent("");
        printTobTobBoundary();
        printTobTobIndent("Tob Tob! Who's there?");
        printTobTobIndent("What do you want Tob Tob to do for you today?");
        printTobTobBoundary();
    }

    /**
     * Informs the user that there is no file saved before
     */
    public void tobTobNeverMetBefore() {
        printTobTobBoundary();
        printTobTobIndent("We've never met before!");
        printTobTobIndent("Let me introduce myself");
        printTobTobBoundary();
    }
}
