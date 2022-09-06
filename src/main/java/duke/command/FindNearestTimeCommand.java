package duke.command;

import duke.errors.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.TaskList;

public class FindNearestTimeCommand extends Command {
    private long duration;

    /**
     * Constructor for FindCommand
     * @param duration long of keyword of task
     */
    public FindNearestTimeCommand(long duration) {
        this.duration = duration;
    }

    /**
     * Executes the command
     * @param tasks TaskList object that stores tasks
     * @param ui Ui object deals with user interaction
     * @param storage Storage object that handles text file
     * @throws DukeException exception thrown in TaskList, Ui or Storage methods
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printNextTiming(tasks.findTiming(duration));
    }
}
