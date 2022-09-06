package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FriendlyCommand extends Command {

    private String alias;
    private String originalCommand;

    public FriendlyCommand(String alias, String originalCommand) {
        this.alias = alias;
        this.originalCommand = originalCommand;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Parser parser) throws DukeException {
        parser.addAlias(this.alias, this.originalCommand);
        String response = ui.addAlias(this.alias, this.originalCommand);
        return response;
    }
}
