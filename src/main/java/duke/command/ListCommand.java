package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * ListCommand lists all the tasks
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.toNumberedString();
    }

    /**
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * @param o Object we are comparing to.
     * @return boolean whether the object o is an instance of ListCommand.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}
