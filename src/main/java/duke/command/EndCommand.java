package duke.command;

import duke.*;

public class EndCommand extends Command {

    public EndCommand() {
    }
    
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        ui.goodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}