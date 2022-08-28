package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {

    /**
     * String array containing the arguments for the unmark command.
     */
    private String[] arg;

    public UnmarkCommand(String[] arg) {
        this.arg = arg;
    }

    /**
     * Marks task as undone at the specified index in the task list.
     *
     * @param tasks List of tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        int i = Integer.parseInt(arg[1]);
        if (i <= tasks.getCount()) {
            tasks.get(i - 1).setIncomplete();
            storage.write(tasks);

            System.out.println("OK, I have marked this task as not done yet: ");
            System.out.println(tasks.get(i - 1));
        } else {
           throw new DukeException("Index does not exist");
        }
    }
}
