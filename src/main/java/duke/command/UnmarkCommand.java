package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {

    private String[] arg;

    public UnmarkCommand(String[] arg) {
        this.arg = arg;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        int i = Integer.parseInt(arg[1]);
        if (i <= tasks.getCount()) {
            tasks.get(i - 1).incomplete();
            storage.write(tasks);
            System.out.println("OK, I have marked this task as not done yet: ");
            System.out.println(tasks.get(i - 1));
        } else {
           throw new DukeException("Index does not exist");
        }
    }
}
