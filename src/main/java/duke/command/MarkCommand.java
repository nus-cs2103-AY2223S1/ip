package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class MarkCommand extends Command {

    private int taskIndex;

    public MarkCommand(String desc) {
        String numberOnly = desc.replaceAll("[^0-9]", "");
        assert !numberOnly.isEmpty() : "index of task cannot be empty";
        taskIndex = Integer.parseInt(numberOnly);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markTask(taskIndex);
            storage.rebuildFile(tasks.getIterator());
            ui.nextOutput("Alright, this task is marked as done:\n"
                    + tasks.getTask(taskIndex).toString() + "\n"
                    + super.nextAction);
        } catch (IOException ioe) {
            ui.nextOutput("Something went wrong: " + ioe.getMessage());
        }
    }
}
