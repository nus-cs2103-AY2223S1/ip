package duke.commands;

import duke.core.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteTaskCommand extends TaskListIndexCommand {

    public static final String removeTaskText = "Noted. I've removed this task:";

    public DeleteTaskCommand(String invoker, TaskList taskList) {
        super(invoker, taskList);
    }

    @Override
    public String execute(Task t) throws DukeException {
        this.taskList.removeTask(t);
        return removeTaskText + "\n  " + t + "\n" + taskCountText();
    }
}
