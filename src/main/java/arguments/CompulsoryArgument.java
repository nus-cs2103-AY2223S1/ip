package arguments;

import exceptions.DukeException;
import input.Input;

/**
 * Class to represent a compulsory argument which must be provided (can be empty string)
 * @param <T> Type of the argument
 */
public class CompulsoryArgument<T> extends Argument<T> {
    private String missingMessage;
    protected CompulsoryArgument(Input input, String argumentName, String missingMessage) {
        super(input, argumentName);
        this.missingMessage = missingMessage;
    }

    @Override
    public void validate() throws DukeException {
        if (super.value != null) {
            return;
        }

        try {
            String arg = input.getParameter(super.argumentName);
        } catch (DukeException ex) {
            throw new DukeException(missingMessage);
        }
    }
}
