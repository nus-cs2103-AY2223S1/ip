import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Ui {
    /** List of commands */
    private static ArrayList<CommandMatcher> commands;
    /** List of strings to remember */
    private static ArrayList<Task> list;

    /**
     * Styles and prints lines with a border.
     * @param lines Lines to be printed
     */
    public static void messagePrint(String... lines) {
        System.out.println(",----------------------------------------------------------------");
        for (String str : lines) {
            System.out.print("| ");
            System.out.println(str);
        }
        System.out.println("'----------------------------------------------------------------");
    }

    /**
     * Greets user.
     */
    public static void greet() {
        Ui.messagePrint("...where is this again?",
                "Oh, hello, I didn't see you there - I'm Anthea, a chatbot...",
                "...or at least that's what they told me.");
    }

    /**
     * Leaves the user.
     */
    public static void leave() {
        Ui.messagePrint("It was nice to have you around, I'm going back to sleep...");
    }
}
