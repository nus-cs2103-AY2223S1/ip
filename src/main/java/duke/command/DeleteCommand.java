package duke.command;

import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand implements Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a task. Parameters: INDEX. Example: "
            + COMMAND_WORD + " 2";

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task task = taskList.get(index - 1);
        taskList.remove(index - 1);
        ui.printMessage(ui.wrapMessage("Noted. I've removed this task:", task.toString(), taskList));
    }
}
