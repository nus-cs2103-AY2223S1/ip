package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public interface ICommand {
    void execute(Storage storage, TaskList taskList, Ui ui);

    boolean isExit();
}
