package duke.command;

import duke.Storage;
import duke.exception.InvalidDescriptionException;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Represents a ToDo Command
 */
public class TodoCommand extends Command {
    String desc;

    /**
     * Creates a ToDo Command object
     */
    public TodoCommand(String desc) throws InvalidDescriptionException {
        if (desc.isEmpty()) {
            throw new InvalidDescriptionException();
        }

        this.desc = desc;
    }

    /**
     * Creats a ToDo object
     * adds it to tasklist
     * saves tasklist to task file
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.addTask(new ToDo(this.desc));
        storage.saveTaskFile(taskList);
    }
}
