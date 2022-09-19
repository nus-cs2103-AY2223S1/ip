package arguments;

import exceptions.DukeException;
import input.Input;

/**
 * Argument for id of a task in the list (integer)
 */
public class TaskIdArgument extends Argument<Integer> {
    public TaskIdArgument(Input input) {
        super(input, "id");
    }
    public TaskIdArgument() {
        super("id");
    }
    @Override
    public String getUsage() {
        return formatHelp("1", false);
    }

    // This method exists so we can process arbitary no. of arguments and get error messages for all to show to user
    @Override
    public void validate() throws DukeException {
        // validate was already called
        if (super.value != null) {
            return;
        }

        try {
            String idParam = input.getParameter(super.argumentName);
            super.value = Integer.parseInt(idParam);
        } catch (NumberFormatException ex) {
            throw new DukeException("Task number should be an integer e.g 1 :)");
        } catch (DukeException ex) {
            throw new DukeException("This command needs a task number e.g 1");
        }

        assert super.value != null;
    }
}
