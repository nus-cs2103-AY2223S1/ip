package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.DukeException;
import duke.Ui;

public class EventCommand extends Command {
    private String description;
    private String at;

    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event = new Event(description, at);
        taskList.addTask(event);
        ui.printMessage("+ Added this event:\n" + event + "\nNow you have " + taskList.listSize()
                + " tasks in the list\n");
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
