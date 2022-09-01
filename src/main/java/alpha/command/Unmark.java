package alpha.command;

import alpha.AlphaException;
import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

import java.io.IOException;

public class Unmark extends Command {

    private int taskNumber;

    public Unmark(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui uI, FileOperations fileOperations) throws AlphaException {
        taskList.modifyTaskStatus(taskNumber, false);
        fileOperations.rewriteFile(taskList);
        uI.colouredPrint(uI.ANSI_BLUE, ">> " + "unmarked: Task " + this.taskNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj instanceof Unmark) {
            Unmark um = (Unmark) obj;
            return (um.taskNumber == this.taskNumber);
        }
        return false;
    }
}
