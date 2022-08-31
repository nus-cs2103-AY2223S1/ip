package duke.services;

/** Handles display */
public class UI {
    /**
     * Prints Duke's greeting on opening the app
     */
    public static void introduceSelf() {
        sayLines(new String[] {
            "Hello! I'm Duke",
            "What can I do for you?",
        });
    }

    /**
     * Displays the lines using a format
     */
    public static void sayLines(String[] lines) {
        System.out.println("____________________________________________________________");
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints Duke's closing statement on exiting the app
     */
    public static void sayGoodbye() {
        sayLines(new String[] {
            "Bye. Hope to see you again soon!",
        });
    }
}
