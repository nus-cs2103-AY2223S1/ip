package duke.command;

import duke.Storage;
import duke.TaskList;

public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }


    public String execute(TaskList tasks, Storage storage) {
        tasks.get(index).unmark();
        return "I've marked this task as undone: \n" + tasks.get(index);
    }

}
