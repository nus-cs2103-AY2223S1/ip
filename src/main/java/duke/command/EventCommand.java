package duke.command;

import duke.task.Event;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class EventCommand extends Command {

    private String description;
    private String remark;

    public EventCommand(String description, String remark) {
        this.description = description;
        this.remark = remark;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(description, remark);
        tasks.add(event);
        ui.eventTask(tasks, event);
        storage.update(tasks.getTasks());
    }
}
