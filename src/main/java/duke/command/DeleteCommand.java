package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private final String userInput;

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        String message = "";

        String taskDetails = userInput.split(" ", 2)[1];
        int taskIndex = Integer.parseInt(taskDetails);
        Task task = taskList.deleteTask(taskIndex);

        message += "Noted. I've removed this task:\n";
        message += "\t" + task.toString() + "\n";
        message += "Now you have " + taskList.getTaskListSize() + " tasks in the list.\n";
        ui.printMessage(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
