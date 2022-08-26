package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public interface Command {

    void execute(TaskList tasks, Ui ui, Storage storage);
    boolean isExit();

}
