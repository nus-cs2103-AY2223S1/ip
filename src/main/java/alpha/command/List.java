package alpha.command;

import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

public class List extends Command {
    @Override
    public void execute(TaskList taskList, Ui uI, FileOperations fileOperations) {
        taskList.printTasks(uI);
    }
}
