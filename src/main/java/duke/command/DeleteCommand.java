package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    public static final String COMMAND_NAME = "delete";

    private final int delIndex;

    public DeleteCommand(int delIndex) {
        this.delIndex = delIndex;
    }

    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task delTask = tasks.getTask(this.delIndex);
            tasks.deleteTask(this.delIndex);
            ui.showReply(String.format("Understood, I've deleted the following task:\n  %s\nYou now have %d tasks remaining.\n", delTask, tasks.getLength()));
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I do not have a task with that number in my list.", e);
        }
    }

    @Override
    public boolean isTerminator() {
        return false;
    }
}
