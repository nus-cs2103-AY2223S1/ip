package duke;

import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Handles console-based user interface. Used for sanity checks.
 */
public class ConsoleUi implements UiInterface {
    /**
     * {@inheritDoc}
     */
    @Override
    public Reader getReader() {
        return new InputStreamReader(System.in);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printStyledMessage(String... lines) {
        System.out.println(",----------------------------------------------------------------");
        for (String str : lines) {
            assert str != null;
            System.out.print("| ");
            System.out.println(str);
        }
        System.out.println("'----------------------------------------------------------------");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void greet() {
        printStyledMessage("...where is this again?",
                "Oh, hello, I didn't see you there - I'm Anthea, a chatbot...",
                "...or at least that's what they told me.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void leave() {
        printStyledMessage("It was nice to have you around, I'm going back to sleep...");
    }
}
