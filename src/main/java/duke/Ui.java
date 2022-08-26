package duke;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {

    /**
     * Styles and prints lines with a border.
     * @param lines Lines to be printed
     */
    public static void printStyledMessage(String... lines) {
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
        Ui.printStyledMessage("...where is this again?",
                "Oh, hello, I didn't see you there - I'm Anthea, a chatbot...",
                "...or at least that's what they told me.");
    }

    /**
     * Leaves the user.
     */
    public static void leave() {
        Ui.printStyledMessage("It was nice to have you around, I'm going back to sleep...");
    }
}
