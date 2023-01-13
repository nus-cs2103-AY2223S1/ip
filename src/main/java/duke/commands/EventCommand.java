package duke.commands;

import java.time.LocalDateTime;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;

/**
 * Represents an event command
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String SHORTER_COMMAND_WORD = "e";

    private final Event eventTask;

    /**
     * Creates a new instance of event command and event task.
     *
     * @param description The description of the event task.
     * @param deadlineTiming The datetime of the event task.
     */
    public EventCommand(String description, LocalDateTime deadlineTiming) {
        this.eventTask = new Event(description, deadlineTiming);
    }

    /**
     * Adds the event task to the task list and save it to the local file.
     *
     * @param taskList The list of tasks in Duke.
     * @param ui The TextUi class used to print message in Duke.
     * @param storage The storage used to save the tasks in the local file.
     * @return The success message after adding an event task.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.eventTask);
        storage.appendTaskToFile(this.eventTask);
        return ui.getAddTaskCommandMessage(this.eventTask, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
