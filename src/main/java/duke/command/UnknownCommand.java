package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Class to encapsulate a unrecognized command.
 */
public class UnknownCommand extends Command {

    /**
     * Constructor for UnknownCommand.
     */
    public UnknownCommand() {
        super();
    }



    /**
     * Give a Command that does nothing.
     * @return Message of not recognizing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return "I'm sorry, I don't understand what you mean.";
    }
}

