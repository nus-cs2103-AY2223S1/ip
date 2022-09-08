package dukeprogram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * An association class that defines what the main program should do
 * in response to a parsed command
 */
public class InternalAction {
    private final Optional<Runnable> runnable;
    private final List<DukeResponse> displayTexts = new ArrayList<>();

    /**
     * Creates an InternalAction
     * @param displayText the text to display to the user, if any
     * @param runnable the action to perform, if any
     */
    public InternalAction(String displayText, Runnable runnable) {
        this.displayTexts.add(new DukeResponse(displayText));
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
        this.displayTexts.addAll(
                Arrays.stream(displayTexts)
                        .map(DukeResponse::new)
                        .collect(Collectors.toList())
        );
        this.runnable = Optional.empty();
    }

    /**
     * Creates an InternalAction
     * @param responses the responses to display to the user, which is a text followed by a widget
     */
    public InternalAction(DukeResponse... responses) {
        this.displayTexts.addAll(List.of(responses));
        this.runnable = Optional.empty();
    }

    /**
     * Creates an InternalAction
     * @param runnable the action to perform, if any
     */
    public InternalAction(Runnable runnable) {
        this.runnable = Optional.of(runnable);
    }

    public DukeResponse[] getAllResponses() {
        return displayTexts.toArray(DukeResponse[]::new);
    }

    public void doRunnable() {
        runnable.ifPresent(Runnable::run);
    }
}
