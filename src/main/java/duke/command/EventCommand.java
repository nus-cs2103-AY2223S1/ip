package duke.command;

import duke.task.Event;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class EventCommand extends Command {

    private final String description;
    private final String remark;

    public EventCommand(String description, String remark) {
        this.description = description;
        this.remark = remark;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(description, remark);
        tasks.add(event);
        ui.EventTask(tasks, event);
        storage.update(tasks.getTasks());
    }
}
