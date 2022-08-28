package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;

/**
 * The DeadlineCommand class represents user command deadline.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    private String[] userDescription;

    /**
     * Constructor of DeadlineCommand that takes in
     * String Array represting user input.
     * @param userDescription Specified user input.
     */
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
