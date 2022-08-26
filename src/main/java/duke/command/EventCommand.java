package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

public class EventCommand extends Command {
    private final Event event;

    public EventCommand(Event event) {
        super(false);
        this.event = event;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.addTask(event);
        ui.printAddTaskReply(event.toString(), tasklist.getNumOfTask());
        storage.overwriteFile(tasklist.toStorageString());
    }
}
