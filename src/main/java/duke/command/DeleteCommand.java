package duke.command;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;
import duke.exception.MarkException;

public class DeleteCommand extends Command{
    private int num;

    public DeleteCommand(int num) {
        this.num = num;
    }

    /**
     * Deletes the specified task in the task list.
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
        System.out.println("Noted, I've removed this task:\n" + taskList.getTask(num - 1));
        taskList.delete(num - 1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
        storage.save(taskList.list());
    }
}
