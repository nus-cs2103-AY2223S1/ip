package alpha.command;

import alpha.AlphaException;
import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

import java.io.IOException;
import java.util.AbstractList;

public class Delete extends Command {
    private int taskNumber;

    public Delete(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui uI, FileOperations fileOperations) throws AlphaException {
        taskList.deleteTask(taskNumber);
        fileOperations.rewriteFile(taskList);
        uI.colouredPrint(uI.ANSI_BLUE, ">> " + "deleted: Task " + this.taskNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj instanceof Delete) {
            Delete d = (Delete) obj;
            return (d.taskNumber == this.taskNumber);
        }
        return false;
    }
}
