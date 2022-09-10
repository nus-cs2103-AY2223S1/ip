package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Invalid command if user input is unknown command.
 */
public class InvalidCommand extends Command {

    /**
     * Constructor for ListCommand.
     *
     * @param info Type of command
     */
    public InvalidCommand(String[] info) {
        super("Invalid");
    }

    /**
     * Executes the invalid command.
     *
     * @param ui Ui to show operation messages
     * @param taskList TaskList to execute command
     * @return invalid message
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        return "Please enter command with valid arguments!";
    }
}
