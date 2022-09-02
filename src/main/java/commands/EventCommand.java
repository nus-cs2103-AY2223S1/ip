package commands;

import duke.Storage;
import duke.Ui;
import tasks.Events;
import tasks.Task;
import tasks.TaskList;

/**
 * EventCommand creates and adds a new event task to the task list.
 */
public class EventCommand extends Command {
    String descript;
    String atTime;

    /**
     * Constructor for EventCommand.
     *
     * @param descript Description of event.
     * @param atTime Time at which event is to take place.
     */
    public EventCommand(String descript, String atTime) {
        this.descript = descript;
        this.atTime = atTime;
    }

    @Override
    /**
     * Executes EventCommand by creating and adding new Events object to the task list.
     *
     * @param taskList Task list to add new Events task to.
     */
    public String execute(TaskList taskList, Ui ui, Storage s) {
        Task toAdd = new Events(this.descript, this.atTime);
        taskList.addTask(toAdd);
        return Ui.printAddStatement(toAdd.toString(), taskList.getSize());
    }
}
