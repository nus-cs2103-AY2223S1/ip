package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

/**
 * A command that reads and prints all of the tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Constructor for ListCommand.
     *
     * @param command "list".
     */
    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        return;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        String s = taskList.toString();
        if (s.isEmpty()) {
            ui.println("🙁 OOPS!!! There are no tasks in your list yet.");
        } else {
            ui.printWithDivider(String.format("Here are the tasks in your list:\n%s", s));
        }
    }
}
