package duke.ui;

import java.util.Scanner;

/**
 * Encapsulates a DukeUi
 */
public class DukeUi {
    private static Scanner sc;

    /**
     * Constructor for a DukeUi
     */
    public DukeUi() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns True if there is another line of input False otherwise
     *
     * @return A boolean to indicate if there is another line of input
     */
    public boolean hasNextLine() {
        return sc.hasNextLine();
    }

    /**
     * Returns the line of input from the user
     *
     * @return The next line of input
     */
    public String getNextLine() {
        return sc.nextLine();
    }

    /**
     * Ends service
     */
    public void endService() {
        sc.close();
    }

    /**
     * Prints customised Duke message
     */
    public static void dukePrint(String str) {
        System.out.println("===========================================\n");
        System.out.println(str);
        System.out.println("===========================================\n");
    }
}
