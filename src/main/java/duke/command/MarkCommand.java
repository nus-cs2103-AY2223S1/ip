package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;
import duke.exception.MarkException;

public class MarkCommand extends Command {
    private int num;

    public MarkCommand(int num) {
        this.num = num;
    }

    /**
     * Marks the specified task as done.
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
        taskList.getTask(num - 1).setDone(true);
        System.out.println("Nice! I've marked this task as done:\n" + taskList.getTask(num - 1));
        storage.save(taskList.list());
    }
}
