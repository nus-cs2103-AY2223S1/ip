package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddItemCommand implements Command {
    private final String[] input;
    private final Parser parser;

    public AddItemCommand(String[] input, Parser parser) {
        this.input = input;
        this.parser = parser;
    }

    /**
     * {@inheritDoc}
     * Adds given input item to itemList, and shows the resulting message to the user.
     */
    @Override
    public void execute(TaskList itemList, Ui ui, Storage storage) throws DukeException {
        ui.showToUser(parser.parseAddItem(input));
    }
}
