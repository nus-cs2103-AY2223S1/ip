package dukeprogram;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * An association class that defines what the main program should do
 * in response to a parsed command
 */
public class InternalAction {
    private final Optional<Runnable> runnable;
    private final List<String> displayTexts = new ArrayList<>();

    /**
     * Creates an InternalAction
     * @param displayText the text to display to the user, if any
     * @param runnable the action to perform, if any
     */
    public InternalAction(String displayText, Runnable runnable) {
        this.displayTexts.add(displayText);
        this.runnable = Optional.of(runnable);
    }

    /**
     * Creates an empty InternalAction
     */
    public InternalAction() {
        this.runnable = Optional.empty();
    }

    /**
     * Creates an InternalAction
     * @param displayTexts the texts to display to the user, if any
     */
    public InternalAction(String... displayTexts) {
        this.displayTexts.addAll(List.of(displayTexts));
        this.runnable = Optional.empty();
    }

    /**
     * Creates an InternalAction
     * @param runnable the action to perform, if any
     */
    public InternalAction(Runnable runnable) {
        this.runnable = Optional.of(runnable);
    }

    public String[] getAllDisplayText() {
        return displayTexts.toArray(String[]::new);
    }

    public void doRunnable() {
        runnable.ifPresent(Runnable::run);
    }
}
