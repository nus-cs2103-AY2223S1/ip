package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;

/**
 * The EventCommand class represents user command event.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private String[] userDescription;

    /**
     * Constructor of EventCommand that takes in
     * String Array represting user input.
     * @param userDescription Specified user input.
     */
    public EventCommand(String[] userDescription) {
        this.userDescription = userDescription;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task event = new Event(userDescription[0], userDescription[1]);
            tasks.addTask(event);
            storage.save(tasks.getTaskList());
            ui.showAddTask(event, tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please use /at to specify event time.");
        }

    }
}
