package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;
import duke.exception.MarkException;

public class UnmarkCommand extends Command {
    private int num;

    public UnmarkCommand(int num) {
        this.num = num;
    }

    /**
     * Marks the specified task as not done.
     *
     * @param storage {@inheritDoc}
     * @param ui {@inheritDoc}
     * @param taskList {@inheritDoc}
     * @throws MarkException If number specified is out of range of the task list.
     */
    @Override
    public void execute(Storage storage, UI ui, TaskList taskList) throws MarkException {
        if (num > taskList.size() || num < 0) {
            throw new MarkException();
        }
        taskList.getTask(num - 1).setDone(false);
        System.out.println("Ok, I've marked this task as not done yet:\n" + taskList.getTask(num - 1));
        storage.save(taskList.list());
    }
}