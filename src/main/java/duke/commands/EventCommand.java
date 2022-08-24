package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.ui.Ui;

public class EventCommand extends Command {

    private String description;
    private String at;

    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Event event = new Event(this.description, this.at);
        taskList.addToList(event);
        storage.save(taskList);
        ui.printAddTask(event, taskList.getSize());
    }
}
