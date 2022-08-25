/**
 * Creates a new event task
 */
package Command;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Event;

import java.time.LocalDate;

public class EventCommand extends Command {
    private String desc;
    private LocalDate date;

    public EventCommand(String desc, LocalDate date) {
        super();
        this.desc = desc;
        this.date = date;
    }

    /**
     * Creates a new event task and adds it to the current tasklist,
     * then saves it into the file and prints the output nicely
     * with the ui class so that user can understand what is happening.
     *
     * @param t which contains the current tasklist
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     */
    @Override
    public void execute(TaskList t, Ui ui, Storage storage) {
        Event e = new Event(this.desc, this.date);
        t.addTask(e);
        storage.writeFile(t.tasksToString());
        ui.printAddTask(e, t.getSize());
    }
}
