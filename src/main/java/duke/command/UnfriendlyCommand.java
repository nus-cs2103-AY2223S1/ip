package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnfriendlyCommand extends Command {

    private String alias;

    public UnfriendlyCommand(String alias) {
        this.alias = alias;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Parser parser) throws DukeException {
        parser.deleteAlias(this.alias);
        String response = ui.deleteAlias(this.alias);
        return response;
    }
}
