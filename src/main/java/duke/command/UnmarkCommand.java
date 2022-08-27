package duke.command;


import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

import duke.exception.TaskNotFoundException;
import duke.exception.TaskUnmarkException;

import duke.task.Task;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private static final String TASK_UNMARK = "Aw... Strive to finish it soon! Will mark it as undone: ";
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskUnmarkException, TaskNotFoundException {
        Task task = tasks.unmarkTask(index);
        storage.update(tasks);
        displayCommand(ui, TASK_UNMARK, task, tasks.getStatus());
    }

}
