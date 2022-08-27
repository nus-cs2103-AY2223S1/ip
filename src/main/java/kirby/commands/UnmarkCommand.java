package kirby.commands;

import kirby.TaskList;
import kirby.Ui;
import kirby.Storage;
import kirby.exceptions.KirbyMissingArgumentException;
import java.io.IOException;

public class UnmarkCommand extends Command {
    private String inputString;
    public UnmarkCommand(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        if (inputString.split(" ").length != 2) {
            throw new KirbyMissingArgumentException("unmark");
        }
        int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
        int currentTaskCount = tasks.getTaskCount();
        if (taskIndex < 1 || taskIndex > currentTaskCount) {
            throw new KirbyMissingArgumentException("unmark");
        }
        tasks.setTaskUnmarked(taskIndex - 1);

        try {
            storage.writeTask(tasks.getList());
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
