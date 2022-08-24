package duke.command;

import duke.Ui;
import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void handle(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        Task t = taskList.deleteTask(this.index);
        ui.line();
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        taskList.printArraySize();
        ui.line();
    }
}
