package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class TagCommand extends Command {
    private String tag;
    private final int taskNum;

    public TagCommand(int taskNum, String tag) {
        this.tag = tag;
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new DukeException("OOPS!!! The index of the task is not in the list.");
        }

        System.out.println(taskNum);
        System.out.println(tag);

        tasks.tagTask(taskNum, tag);
        ui.outputMessage("Successfully tagged task No." + (taskNum + 1) + " with # " + tag);
    }
}
