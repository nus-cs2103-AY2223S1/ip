package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    private String[] userDescription;
    public DeadlineCommand(String[] userDescription) {
        this.userDescription = userDescription;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task deadline = new Deadline(userDescription[0], userDescription[1]);
            tasks.addTask(deadline);
            storage.save(tasks.getTaskList());
            ui.showAddTask(deadline, tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please use /at to specify event time.");
        }
    }
}
