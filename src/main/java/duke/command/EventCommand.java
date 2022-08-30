/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

/**
 * public class EventCommand that handles the command for Event.
 */
public class EventCommand extends Command{
    private String event;
    private LocalDate time;

    /**
     * public constructor for EventCommand.
     * @param event the name of event.
     * @param time the time/date of event.
     */
    public EventCommand(String event, LocalDate time) {
        super();
        this.event = event;
        this.time = time;
    }

    /**
     * public method execute to execute command.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Event e = new Event(this.event, this.time);
        tasks.addTask(e);
        storage.saveToFile(tasks.saveList());
        return ui.printAddTask(e, tasks.getSize());
    }
}
