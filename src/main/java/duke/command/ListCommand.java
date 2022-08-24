package duke.command;

import duke.DukeException;

/**
 * Command to list all {@code Task}s in the {@code TaskList}.
 */
public class ListCommand extends Command {

    /**
     * Prints all {@code Task}s in the {@code TaskList}. .
     */
    @Override
    public void execute() throws DukeException {
        String[] tasks = Command.taskList.getAllTasksInDisplayFormat().toArray(new String[0]);
        if (tasks.length == 0) {
            Command.ui.printMessages(new String[]{"No tasks"});
        } else {
            Command.ui.printMessages(tasks);
        }
    }
}
