package command;

import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;
import command.Command;

public class List extends Command {
    @Override
    public void execute(TaskList taskList, Ui uI, FileOperations fileOperations) {
        taskList.printTasks(uI);
    }
}
