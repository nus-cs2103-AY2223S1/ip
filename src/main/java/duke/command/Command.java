package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Objects;

public class Command {
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

    public boolean isExit() {
        return this.isExit;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {

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
