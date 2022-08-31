package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {
    private final String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        String message = "";

        if (userInput.split(" ").length == 1) {
            throw new DukeException("☹ OOPS!!! The find command cannot have a missing query.");
        }
        String query = userInput.split(" ")[1];
        message += "Here are the matching tasks in your list:\n";

        int index = 1;
        for (int i = 0; i < taskList.getTaskListSize(); i++) {
            Task currentTask = taskList.getTaskAtIndex(i + 1);
            if (currentTask.toString().contains(query)) {
                message += index + ". " + currentTask + "\n";
                index += 1;
            }
        }
        ui.printMessage(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}