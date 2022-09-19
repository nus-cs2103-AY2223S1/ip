package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index - 1;
    }
    /**
     * Executes the command to remove tasks from the task list, given the index.
     *
     * @param storage  Storage handling the file IO.
     * @param taskList A list of tasks.
     * @param ui       A ui to handle printing output.
     * @return notification that the work has been done
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        assert storage != null;
        assert taskList != null;
        assert ui != null;
        // this taskList might need to be after the return statement
        String output = String.format("Noted. I've removed this task:\n%s\nNow you have %s tasks in the list.\n",
                taskList.get(index).toString(), (taskList.size() - 1));

        taskList.remove(index);
        return output;
    }
}