package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public interface Command {

    void execute(TaskList tasks, Ui ui, Storage storage);
    boolean isExit();

}
