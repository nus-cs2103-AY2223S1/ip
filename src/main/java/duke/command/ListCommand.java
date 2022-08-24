package duke.command;

import duke.Ui;
import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class ListCommand extends Command {

    @Override
    public void handle(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        int numOfTasks = taskList.getSize();
        if (numOfTasks == 0) {
            throw new DukeException("Unfortunately, you do not have any tasks at hand." +
                    " Try creating some first.");
        }
        ui.line();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= numOfTasks; i++) {
            Task t = taskList.getTask(i);
            System.out.println(i + "." + t);
        }
        ui.line();
    }
}
