package alpha.command;

import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

import java.time.DateTimeException;

public class List extends Command {
    @Override
    public void execute(TaskList taskList, Ui uI, FileOperations fileOperations) throws DateTimeException {
        taskList.printTasks(uI);
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj instanceof List) {
            return true;
        }
        return false;
    }
}
