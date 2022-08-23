package duke.command;

import duke.tasklist.TaskList;
import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
public class AddCommand extends Command{
    private String fullCommand;
    public AddCommand(String fullCommand){
        this.fullCommand = fullCommand;
    }
    @Override
    public void execute(TaskList taskList, Storage
                         storage) throws DukeException{
        taskList.addTask(Task.createATask(fullCommand));
        storage.updateFile(taskList.getTaskList());
    }
}
