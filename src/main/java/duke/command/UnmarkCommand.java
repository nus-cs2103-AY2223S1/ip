package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(String desc) {
        String numberOnly = desc.replaceAll("[^0-9]", "");
        index = Integer.parseInt(numberOnly);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmarkTask(index);
            storage.rebuildFile(tasks.getIterator());
            ui.printMessage("Alright, this task is marked as not done yet:\n"
                    + tasks.getTask(index).toString());
        } catch (IOException ioe) {
            System.out.println("Something went wrong: " + ioe.getMessage());
        }
    }
}
