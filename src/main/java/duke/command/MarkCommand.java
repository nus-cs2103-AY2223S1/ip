package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command{

    /**
     * String array containing arguments for the mark command.
     */
    private String[] arg;

    public MarkCommand(String[] arg) {
        this.arg = arg;
    }

    /**
     * Marks the task at the specified index as done if it exists.
     * @param tasks List of tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int i = Integer.parseInt(arg[1]);
        if (i  <= tasks.getCount()) {
            tasks.get(i - 1).complete();
            storage.write(tasks);
            System.out.println("Nice! I have marked this task as done: ");
            System.out.println(tasks.get(i - 1));
        } else {
            throw new DukeException("Index does not exist");
        }
    }
}
