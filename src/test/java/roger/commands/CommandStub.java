package roger.commands;

import roger.storage.Storage;
import roger.tasks.TaskList;


/**
 * Testing stub for Command class
 */
public class CommandStub extends Command {
    public CommandStub() {
    }

    public String execute(TaskList tasks, Storage storage) {
        return "";
    }
}
