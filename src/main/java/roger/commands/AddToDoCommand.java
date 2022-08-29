package roger.commands;

import roger.storage.Storage;
import roger.tasks.TaskList;
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
     * @param storage The storage to read and load to.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        ToDo toDo = new ToDo(taskName);
        tasks.add(toDo);
        return "Nephew got new to-do:\n"
                + toDo.toString() + "\n"
                + "Nephew now have " + Integer.toString(tasks.getLength()) + " tasks in the list.";
    }
}
