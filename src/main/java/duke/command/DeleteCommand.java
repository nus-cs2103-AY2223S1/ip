package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {

    private String numberOnly;

    public DeleteCommand(String desc) {
        numberOnly = desc.replaceAll("[^0-9]", "");
        assert !numberOnly.isEmpty() : "index of task to be deleted cannot be empty";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(numberOnly);
            Task deleted = tasks.getTask(index);
            tasks.deleteTask(index);
            storage.rebuildFile(tasks.getIterator());
            ui.nextOutput("Noted. I've removed this task:\n"
                    + deleted.toString() + "\n"
                    + "Now you have " + tasks.listSize() + " tasks in the list");
        } catch (IOException ioe) {
            ui.nextOutput("Something went wrong: " + ioe.getMessage());
        }
    }
}
