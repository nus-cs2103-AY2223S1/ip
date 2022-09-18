package arguments;

import exceptions.DukeException;
import input.Input;

/**
 * Argument for a string that should be non-empty - enables re-use for any such argument
 */
public class StringArgument extends Argument<String> {
    private String emptyMessage;
    private String missingMessage;

    /**
     * Creates new StringArgument with necessary parameters
     * @param input Input object
     * @param argumentName Name for argument
     * @param emptyMessage Message to show if argument is empty
     * @param missingMessage Message to show if argument is missing
     */
    public StringArgument(Input input, String argumentName, String emptyMessage, String missingMessage) {
        super(input, argumentName);
        this.emptyMessage = emptyMessage;
        this.missingMessage = missingMessage;
    }

    /**
     * Creates new StringArgument with necessary parameters
     * @param argumentName Name for argument
     * @param emptyMessage Message to show if argument is empty
     * @param missingMessage Message to show if argument is missing
     */
    public StringArgument(String argumentName, String emptyMessage, String missingMessage) {
        super(argumentName);
        this.emptyMessage = emptyMessage;
        this.missingMessage = missingMessage;
    }
    @Override
    public String getUsage() {
        return null;
    }

    @Override
    public void validate() throws DukeException {
        if (super.value != null) {
            return;
        }

        try {
            String text = input.getParameter(super.argumentName);
            super.value = text;

            if (text.trim().equals("")) {
                throw new EmptyArgumentException(emptyMessage);
            }
        } catch (DukeException ex) {
            throw new DukeException(missingMessage);
        } catch (EmptyArgumentException e) {
            throw new DukeException(e.getMessage());
        }

        assert super.value != null;
    }
}
