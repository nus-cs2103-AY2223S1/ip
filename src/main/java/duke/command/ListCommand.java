package duke.command;

import duke.task.TaskList;

/**
 * List Command class.
 */
public class ListCommand extends Command{

    /**
     * Prints all the tasks in tasks.
     *
     * @param tasks The tasks to be executed.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.printList();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
