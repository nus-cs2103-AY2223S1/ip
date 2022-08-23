package duke.commands;

import duke.Ui;
import duke.task.TaskList;

/**
 * Lists all existing tasks.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.displayText("Here are the tasks in your list:\n" + tasks.toString());
    }
}
