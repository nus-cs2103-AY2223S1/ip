package duke.command;

import duke.duke.DukeException;
import duke.util.Storage;
import duke.task.TaskList;
import duke.task.Task;

/** Represents the command to delete task that inherits from Command. */
public class DeleteCommand extends Command {
    private final String userInput;

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        String message = "";

        String taskDetails = userInput.split(" ", 2)[1];
        int taskIndex = Integer.parseInt(taskDetails);
        Task task = taskList.deleteTask(taskIndex);

        message += "Noted. I've removed this task:\n";
        message += "\t" + task.toString() + "\n";
        message += "Now you have " + taskList.getTaskListSize() + " tasks in the list.\n";
        return message;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
