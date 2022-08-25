package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ExitCommand extends Command {
    
    ExitCommand(CommandType commandType) {
        super(commandType);
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {

    }
}
