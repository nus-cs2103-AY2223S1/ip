/*
package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class UndoCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println(taskList.getTasks());
        System.out.println(taskList.getUndoTasks());
        if (taskList.getTasks() != taskList.getUndoTasks()) {
            taskList.makeBothTaskEqual();
        }
        storage.saveFile(taskList);
        return ui.showUndo();
    }
}

 */