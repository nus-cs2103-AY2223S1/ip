package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class MarkCommand extends Command {

    private int taskIndex;

    public MarkCommand(String desc) {
        String numberOnly = desc.replaceAll("[^0-9]", "");
        taskIndex = Integer.parseInt(numberOnly);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markTask(taskIndex);
            storage.rebuildFile(tasks.getIterator());
            ui.nextOutput("Alright, this task is marked as done:\n"
                    + tasks.getTask(taskIndex).toString());
        } catch (IOException ioe) {
            ui.nextOutput("Something went wrong: " + ioe.getMessage());
        }
    }
}
