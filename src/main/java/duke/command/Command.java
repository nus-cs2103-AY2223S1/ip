package duke.command;

import java.util.Objects;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Generic Command parent class.
 */
public abstract class Command {
    protected Task task;
    protected int index;
    protected boolean isExit = false;

    Command(int index) {
        this.index = index;
    }

    Command(Task task) {
        this.task = task;
    }

    Command() {
    }

    public boolean getExit() {
        return this.isExit;
    }

    /**
     * Executes command, adding/deleting/modifying stored task.
     *
     * @param tasks   TaskList object corresponding to all tasks
     * @param ui      Ui object to show user output/errors
     * @param storage Storage object to save data after execution
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Command command = (Command) o;
        return index == command.index && isExit == command.isExit && task.equals(command.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, index, isExit);
    }
}
