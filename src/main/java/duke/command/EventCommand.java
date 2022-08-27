package duke.command;

import duke.task.Event;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to add an {@code Event} to the {@code TaskList}
 */
public class EventCommand extends Command {

    private String description;
    private String remark;

    /**
     * Constructor for {@code EventCommand}
     * @param description the description of the {@code Event}
     * @param remark the remark of the {@code Event}
     */
    public EventCommand(String description, String remark) {
        this.description = description;
        this.remark = remark;
    }

    /**
     * to execute the {@code EventCommand}
     * @param tasks the current {@code TaskList}
     * @param ui the current {@code Ui}
     * @param storage the current {@code Storage}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(description, remark);
        tasks.add(event);
        ui.eventTask(tasks, event);
        storage.update(tasks.getTasks());
    }
}
