package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class MarkCommand extends Command {
    private int taskIndex;
    
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    
    public static String getFormat() {
        return "mark <Integer>";
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.mark(taskIndex);
        ui.showMarkTask(task, tasks);
        storage.save(tasks);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
