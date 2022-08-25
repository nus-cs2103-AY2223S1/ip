package duke.commands;

import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage);
}
