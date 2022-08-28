package caca;

/**
 * This class deals with interactions with the user.
 *
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class Ui {

    /**
     * A horizontal line to be displayed as separator for each activity with CaCa.
     */
    private static final String LINE = "____________________________________________________________\n";

    /**
     * Greets user with CaCa logo and greeting.
     */
    public static void greet() {
        // ASCII text banner below created and adapted from
        // https://manytools.org/hacker-tools/ascii-banner/
        // with the following settings:
        // Banner text: CaCa, Font: Big, Horizontal spacing: Normal, Vertical spacing: Normal.
        String LOGO = "   _____       _____      \n"
                + "  / ____|     / ____|     \n"
                + " | |     __ _| |     __ _ \n"
                + " | |    / _` | |    / _` |\n"
                + " | |___| (_| | |___| (_| |\n"
                + "  \\_____\\__,_|\\_____\\__,_|\n\n";

        String GREETING = "Hello! I'm CaCa.\n"
                + "What can I do for you?\n";

        System.out.println(LINE + LOGO + GREETING + LINE);
    }

    /**
     * Says bye to user.
     */
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
