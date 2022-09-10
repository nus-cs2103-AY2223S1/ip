package duke.command;

import duke.exception.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ListCommand extends Command {

    public ListCommand() {

    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        int counter = 1;
        this.response = "Here are the tasks in your list:\n";

        for (Task t : taskList.getTaskList()) {
            this.response += counter + "." + t.toString() + "\n";
            counter++;
        }
    }
}
