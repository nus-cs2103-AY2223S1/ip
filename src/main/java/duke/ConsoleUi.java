package duke;

import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Handles console-based user interface. Used for sanity checks.
 */
public class ConsoleUi implements UiInterface {
    private static final String UPPER_BAR = ",----------------------------------------------------------------";
    private static final String LOWER_BAR = "'----------------------------------------------------------------";
    private static final String LEFT_BAR = "| ";

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
        System.out.println(UPPER_BAR);
        for (String str : lines) {
            assert str != null;
            System.out.print(LEFT_BAR);
            System.out.println(str);
        }
        System.out.println(LOWER_BAR);
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
