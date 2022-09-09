package ip.command;

import ip.utility.Storage;
import ip.utility.TaskList;

public class UndoCommand extends DukeCommand {

    @Override
    public String execute(TaskList taskList, Storage storage) {
        taskList.set(storage.getBackupTaskList());
        System.out.println(taskList.listAllTasks());
        storage.saveToLatest(taskList);
        return "Reverted 1 change.";
    }
}
