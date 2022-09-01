/**
 * Creates a new deadline task
 */
package Command;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Deadline;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private String desc;
    private LocalDateTime date;

    public DeadlineCommand(String desc, LocalDateTime date) {
        super();
        this.desc = desc;
        this.date = date;
    }

    /**
     * Creates a new deadline task and adds it into the tasklist,
     * followed by saving it into the filepath given and
     * finally prints the necessary lines for the user interface.
     *
     * @param taskList which contains the current tasklist
     * @param ui which handles the user interface
     * @param storage which handles the saving and loading of file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(this.desc, this.date);
        taskList.addTask(deadline);
        storage.writeFile(taskList.tasksToString());
        ui.printAddTask(deadline, taskList.getSize());
    }
}
