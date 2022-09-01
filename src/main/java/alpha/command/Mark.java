package alpha.command;

import alpha.AlphaException;
import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

import java.io.IOException;

public class Mark extends Command {
    private int taskNumber;

    public Mark(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui uI, FileOperations fileOperations) throws AlphaException {
        taskList.modifyTaskStatus(taskNumber, true);
        fileOperations.rewriteFile(taskList);
        uI.colouredPrint(uI.ANSI_BLUE, ">> " + "marked: Task " + this.taskNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj instanceof Mark) {
            Mark m = (Mark) obj;
            return (m.taskNumber == this.taskNumber);
        }
        return false;
    }
}
