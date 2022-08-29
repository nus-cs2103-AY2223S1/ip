package duke;

/**
 * A class that manages the user interface (i.e. all interactions with the user).
 */
public class Ui {

    /**
     *  Prints out a horizontal line 40 dashes long.
     */
    private void printLine() {
        for (int i = 0; i < 40; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    /**
     * Prints out the string s, wrapped within 2 horizontal lines.
     *
     * @param s The given string to be printed.
     */
    public void say(String s) {
        printLine();
        System.out.println(s);
        printLine();
    }

    /**
     * Prints out an error message, specifically for failed loading.
     */
    public void showLoadingError() {
        say("Failed to load! :(");
    }
}
