package duke.command;

import java.util.ArrayList;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Handles the deletion of a task from
 * a <code>TaskList</code>.
 */
public class DeleteCommand extends Command {

    /**
     * Initialises a DeleteCommand to store the details of
     * the user's input and the TaskList.
     */
    public DeleteCommand(String[] commandArgs, TaskList tasks) {
        super(commandArgs, tasks);
    }

    /**
     * Deletes a task from the TaskList.
     * Returns true to indicate that the programme should continue
     * prompting for user input.
     */
    @Override
    public boolean performAction() {
        Integer index = Integer.parseInt(this.commandArgs[1]) - 1;
        tasks.deleteTaskByIndex(index.intValue());
        return true;
    }
}
