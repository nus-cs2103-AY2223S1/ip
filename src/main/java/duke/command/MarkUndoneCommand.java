package duke.command;

import duke.Ui;
import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class MarkUndoneCommand extends Command {
    private int index;

    public MarkUndoneCommand(int index) {
        this.index = index;
    }

    public void handle(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        Task t = taskList.getTask(index);
        t.markUndone();
        ui.line();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
        ui.line();
    }
}
