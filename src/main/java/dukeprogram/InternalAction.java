package dukeprogram;

import javax.print.attribute.standard.RequestingUserName;
import java.util.Optional;

/**
 * An association class that defines what the main program should do
 * in response to a parsed command
 */
public class InternalAction {
    private final Optional<Runnable> runnable;
    private final Optional<String> displayText;

    /**
     * Creates an InternalAction
     * @param displayText the text to display to the user, if any
     * @param runnable the action to perform, if any
     */
    public InternalAction(String displayText, Runnable runnable) {
        this.displayText = Optional.of(displayText);
        this.runnable = Optional.of(runnable);
    }

    /**
     * Creates an empty InternalAction
     */
    public InternalAction() {
        this.displayText = Optional.empty();
        this.runnable = Optional.empty();
    }

    /**
     * Creates an InternalAction
     * @param displayText the text to display to the user, if any
     */
    public InternalAction(String displayText) {
        this.displayText = Optional.of(displayText);
        this.runnable = Optional.empty();
    }

    /**
     * Creates an InternalAction
     * @param runnable the action to perform, if any
     */
    public InternalAction(Runnable runnable) {
        this.displayText = Optional.empty();
        this.runnable = Optional.of(runnable);
    }

    public String getDisplayText() {
        return displayText.orElse("");
    }

    public void doRunnable() {
        runnable.ifPresent(Runnable::run);
    }
}
