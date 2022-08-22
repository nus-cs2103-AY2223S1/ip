package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class UnmarkCommand extends Command {
    private int taskIndex;
    
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    
    public static String getFormat() {
        return "unmark <Integer>";
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.unmark(taskIndex);
        ui.showUnmarkTask(task, tasks);
        storage.save(tasks);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
