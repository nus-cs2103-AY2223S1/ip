package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class MarkCommand extends Command {
    private final String indexString;
    private final boolean isComplete;

    public MarkCommand(String indexString, boolean isComplete) {
        this.indexString = indexString;
        this.isComplete = isComplete;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.setTaskCompletion(indexString, isComplete);
        storage.saveData(tasks);
        if (isComplete) {
            ui.printMessage("Nice! I've marked this task as done:\n" + task);
        } else {
            ui.printMessage("OK, I've marked this task as not done yet:\n" + task);
        }
    }

    @Override
    public String getCommand() {
        return "mark";
    }

    @Override
    public String toString() {
        return "mark " + indexString + " " + isComplete;
    }
}
