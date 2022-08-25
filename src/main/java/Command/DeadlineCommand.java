/**
 * Creates a new deadline task
 */
package Command;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Deadline;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private String desc;
    private LocalDate date;

    public DeadlineCommand(String desc, LocalDate date) {
        super();
        this.desc = desc;
        this.date = date;
    }

    /**
     * Creates a new deadline task and adds it into the tasklist,
     * followed by saving it into the filepath given and
     * finally prints the necessary lines for the user interface.
     *
     * @param t which contains the current tasklist
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     */
    @Override
    public void execute(TaskList t, Ui ui, Storage storage) {
        Deadline d = new Deadline(this.desc, this.date);
        t.addTask(d);
        storage.writeFile(t.tasksToString());
        ui.printAddTask(d, t.getSize());
    }
}
