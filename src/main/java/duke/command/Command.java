package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public abstract class Command {

    CommandType commandType;

    Command(CommandType commandType) {
        this.commandType = commandType;
    }

    public boolean isExit() {
        return commandType == CommandType.EXIT;
    }
    public abstract void execute(Ui ui, TaskList taskList, Storage storage);
}
