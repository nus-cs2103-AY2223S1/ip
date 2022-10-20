package duke.commands;

import duke.DukeException;
import duke.Task;
import duke.TaskList;

public class DeleteCommand implements Command {
    public final static String COMMAND_WORD = "delete";

    private int displayedTaskIndex;

    public DeleteCommand(int displayedTaskIndex) {
        this.displayedTaskIndex = displayedTaskIndex;
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        Task deletedTask = taskList.deleteTask(displayedTaskIndex);
        return String.format("Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.\n",
                deletedTask.toString(), taskList.getNumberOfTasks());
    }
}
