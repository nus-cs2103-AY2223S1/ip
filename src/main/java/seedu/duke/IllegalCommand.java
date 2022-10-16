package seedu.duke;

/**
 * Represents an Illegal Command operation that is performed by the user.
 */
public class IllegalCommand extends Command {

    public IllegalCommand() {

    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage, String input) throws DukeException {
        String output = "I'm sorry, but I dont know what you mean :(";
        return output;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof IllegalCommand);
    }
}
