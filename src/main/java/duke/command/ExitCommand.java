package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
public class ExitCommand extends Command{
    @Override
    public void execute( TaskList taskList, Storage storage){
        super.setIsExitTrue();
    }
}
