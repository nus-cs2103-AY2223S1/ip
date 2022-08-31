package duke.command;

import duke.Storage;
import duke.TaskList;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList tasks, Storage storage) {
        tasks.get(index).mark();
        return "I've marked this task as done: \n" + tasks.get(index);
    }

}
