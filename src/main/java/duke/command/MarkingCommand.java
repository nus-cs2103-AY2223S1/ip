package duke.command;

import duke.tasklist.TaskList;
import duke.dukeexception.DukeException;
import duke.storage.Storage;
public class MarkingCommand extends Command {
    String fullCommand;
    public MarkingCommand(String fullCommand){
        this.fullCommand = fullCommand;
    }
    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        if (this.fullCommand.split(" ")[0].equals("mark")) {
            taskList.markAsDone(Integer.valueOf(this.fullCommand.split(" ")[1]) - 1, storage);
        } else {
            taskList.markUndone(Integer.valueOf(this.fullCommand.split(" ")[1]) - 1, storage);
        }
    }
}
