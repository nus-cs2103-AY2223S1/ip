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

public class EventCommand extends Command{
    private String event;
    private LocalDate time;

    public EventCommand(String event, LocalDate time) {
        super();
        this.event = event;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event e = new Event(this.event, this.time);
        tasks.addTask(e);
        storage.savetoFile(tasks.saveList());
        ui.printAddTask(e, tasks.getSize());
    }
}
