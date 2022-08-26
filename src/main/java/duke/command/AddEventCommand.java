package duke.command;

import duke.errors.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.Events;
import duke.task.Task;
import duke.task.TaskList;

public class AddEventCommand extends Command {
    private String description;
    private String by;

    public AddEventCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new Events(description, by);
        tasks.add(task);
        ui.addSuccess(task, tasks);
        storage.save(tasks);
    }
}
