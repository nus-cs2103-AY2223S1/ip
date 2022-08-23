package roger.commands;

import roger.Storage;
import roger.TaskList;
import roger.Ui;
import roger.tasks.ToDo;


/**
 * Encapsulates the command to add a to-do.
 */
public class AddToDoCommand extends Command {
    protected String taskName;

    /**
     * Create a AddToDoCommand.
     *
     * @param taskName The to-do name.
     */
    public AddToDoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Add the to-do.
     *
     * @param tasks The TaskList of the Roger program.
     * @param ui The Ui used.
     * @param storage The storage to read and load to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo toDo = new ToDo(taskName);
        tasks.add(toDo);
        ui.showcase("Nephew got new to-do:", toDo.toString());
        ui.show("Nephew now have " + Integer.toString(tasks.getLength()) + " tasks in the list.");
    }
}
