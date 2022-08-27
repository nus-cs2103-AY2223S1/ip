package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String doneTask = tasks.markAsDone(index);
        storage.save(tasks);
        ui.printString("Task marked as done:\n" + doneTask);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MarkCommand)) {
            return false;
        }
        MarkCommand otherCommand = (MarkCommand) o;
        return this.index == otherCommand.index;
    }
}
