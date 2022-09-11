package duke.command;

import duke.util.Ui;

/**
 * Command to exit the chatbot application.
 */
public class ByeCommand extends Command {

    /**
     * Constructor for {@code ByeCommand}.
     */
    public ByeCommand() {
        super.isExit = true;
    }

    /**
     * Adds the given task to the {@code TaskList}, and returns a success message.
     *
     * @return Message {@code String} from command execution.
     */
    @Override
    public String execute() {
        return Ui.formatMessages(new String[]{"Bye. Hope to see you again soon!"});
    }
}
