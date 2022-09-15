package commands;

import byu.TaskList;
import byu.Ui;
import exceptions.InvalidIndexException;

/**
 * A command to exit Byu.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidIndexException {
        String response = generateResponse(tasks);
        ui.setOutput(response);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String generateResponse(TaskList tasks) {
        return "Awww, I'll miss you :(\nSee you soon!!";
    }
}
