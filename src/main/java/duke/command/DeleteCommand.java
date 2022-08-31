package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public String execute(TaskList tasks, Storage storage) {
        Task tmp = tasks.get(index);
        tasks.remove(index);
        return "I've deleted this task: \n" + tmp;
    }

}
