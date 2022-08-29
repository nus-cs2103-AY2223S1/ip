package duke.command;

import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class UnmarkCommand implements Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Mark a task as not done. Parameters: INDEX. Example: "
            + COMMAND_WORD + " 2";

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        try {
            Task task = taskList.get(this.index);
            task.markNotDone();
            ui.printMessage(String.format("OK, I've marked this task as not done yet:\n\t\t %s", task.toString()));
        } catch (IndexOutOfBoundsException e) {
            ui.printMessage("Invalid task index");
        }
    }
}
