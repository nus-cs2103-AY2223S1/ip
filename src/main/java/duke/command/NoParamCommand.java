package duke.command;

import duke.exceptions.UnknownCommandException;
import duke.util.ParsedData;

/**
 * Abstract class to handle commands that requires no argument
 */
abstract class NoParamCommand implements Command {

    private boolean invalid;

    NoParamCommand(ParsedData data) {
        if (data.description.isEmpty()) {
            invalid = false;
        } else {
            invalid = true;
        }
    }

    /**
     * Guards to ensure commands contains no extra parameters
     */
    protected void checkSingleArgumentGuard() throws UnknownCommandException {
        if (invalid) {
            throw new UnknownCommandException();
        }
    }
}
