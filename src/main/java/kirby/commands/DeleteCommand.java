package kirby.commands;

import kirby.TaskList;
import kirby.Ui;
import kirby.Storage;
import kirby.exceptions.KirbyMissingArgumentException;

public class DeleteCommand extends Command {
    private String inputString;
    public DeleteCommand(String inputString) {
        super(inputString);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        if (inputString.split(" ").length != 2) {
            throw new KirbyMissingArgumentException("delete");
        }
        int taskIndex = Integer.parseInt(inputString.split(" ")[1]);
        int currentTaskCount = tasks.getTaskCount();
        if (taskIndex < 1 || taskIndex > currentTaskCount) {
            throw new KirbyMissingArgumentException("delete");
        }
        tasks.removeTask(taskIndex - 1);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
