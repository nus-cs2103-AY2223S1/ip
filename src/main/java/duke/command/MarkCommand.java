package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class MarkCommand extends Command{
    private final String userInput;
    private final boolean isMark;

    public MarkCommand(String userInput, boolean isMark) {
        this.userInput = userInput;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        String message = "";

        if (userInput.split(" ").length == 1) {
            throw new DukeException("☹ OOPS!!! The mark/unmark command cannot have a missing index.");
        }
        String index = userInput.split(" ")[1];
        Task task = taskList.updateTaskStatus(Integer.parseInt(index), isMark);
        if (isMark) {
            message += "Nice! I've marked this task as done:\n";
        } else {
            message += "OK, I've marked this task as not done yet:\n";
        }
        message += task.toString() + "\n";
        ui.printMessage(message);
        storage.saveTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
