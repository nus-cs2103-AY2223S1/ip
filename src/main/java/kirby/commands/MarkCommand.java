package kirby.commands;

import java.io.IOException;
import kirby.TaskList;
import kirby.Ui;
import kirby.Storage;
import kirby.exceptions.KirbyMissingArgumentException;

public class MarkCommand extends Command {
    private String inputString;
    public MarkCommand(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        if (inputString.split(" ").length != 2) {
            throw new KirbyMissingArgumentException("mark");
        }
        int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
        int currentTaskCount = tasks.getTaskCount();
        if (taskIndex < 1 || taskIndex > currentTaskCount) {
            throw new KirbyMissingArgumentException("mark");
        }
        tasks.setTaskMarked(taskIndex - 1);
        try {
            storage.writeTask(tasks.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
