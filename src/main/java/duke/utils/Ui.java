package duke.utils;

/**
 * The UI class abstracts out how the messages are delivered to the user.
 */
public class Ui {
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static void makeLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("\u2015");
        }
        System.out.println();
    }

    /**
     * Wraps the message within two lines.
     * @param message the message to be shown to the user.
     */
    public static void wrapWithLines(String message) {
        makeLine();
        System.out.println(message);
        makeLine();
    }
}
