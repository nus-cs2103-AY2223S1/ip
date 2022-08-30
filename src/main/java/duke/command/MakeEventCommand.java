package duke.command;

import java.time.LocalDate;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Event;

/**
 * A Command which makes an Event object when executed and adds it to a TaskList object when executed.
 */
public class MakeEventCommand extends Command {
    private String description;
    private LocalDate time;

    /**
     * Constructs a MakeEventCommand object which will make an Event object with description and time and add to a
     * TaskList object when executed.
     * @param description Description for the Event object to be created.
     * @param time Time for the Event object to be created.
     */
    public MakeEventCommand(String description, LocalDate time) {
        this.description = description;
        this.time = time;
    }

    /**
     * Makes a new Event object with description and time from the constructor and adds it to taskList.
     * @param taskList TaskList object of the Duke object for the Command object to use.
     * @param ui Ui object of the Duke object for the Command object to use.
     * @param storage Storage object of the Duke object for the Command object to use.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Event newTask = new Event(this.description, this.time);
        taskList.addTask(newTask);
        ui.showTaskAddedOrDeleted(newTask, taskList.getTaskListLength(), true);
    }

    /**
     * Whether the MakeEventCommand should end the Duke object.
     * It should not.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
