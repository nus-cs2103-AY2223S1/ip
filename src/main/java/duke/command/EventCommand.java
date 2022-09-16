package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

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
     *
     * @param tasks   the current {@code TaskList}
     * @param ui      the current {@code Ui}
     * @param storage the current {@code Storage}
     * @return a string to be printed onto the dialog box
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Event event = new Event(description, remark);
        tasks.add(event);
        storage.update(tasks.getTasks());
        return ui.eventTask(tasks, event);
    }
}
