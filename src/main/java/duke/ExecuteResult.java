package duke;

import java.util.List;

/**
 * Represents the result of an execution.
 */
public class ExecuteResult {
    /**
     * Whether the program should exit after this command
     */
    private final boolean shouldExitAfter;

    /**
     * Lines of text to be sent as a reply from this command
     */
    private final List<String> replyLines;

    /**
     * Constructor
     *
     * @param shouldExitAfter Whether the program should exit after this command
     * @param replyLines      The reply message
     */
    public ExecuteResult(boolean shouldExitAfter, List<String> replyLines) {
        this.shouldExitAfter = shouldExitAfter;
        this.replyLines = replyLines;
    }

    public boolean shouldExitAfter() {
        return shouldExitAfter;
    }

    public List<String> getReplyLines() {
        return this.replyLines;
    }
}
