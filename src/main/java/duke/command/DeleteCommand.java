package duke.command;

import duke.tasklist.TaskList;
import duke.dukeexception.DukeException;
import duke.storage.Storage;
public class DeleteCommand extends Command{
    String fullCommand;
    public DeleteCommand(String fullCommand){
        this.fullCommand = fullCommand;
    }
    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException{
        taskList.delete(Integer.valueOf(this.fullCommand.split(" ")[1])
                , storage);
    }
}
