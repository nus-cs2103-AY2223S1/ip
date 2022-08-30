import java.io.IOException;

public class Mark extends Command{
    private int taskNumber;

    public Mark(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList taskList, Ui uI, FileOperations fileOperations) throws IOException {
        taskList.modifyTaskStatus(taskNumber, true);
        fileOperations.rewriteFile(taskList);
        uI.colouredPrint(uI.ANSI_BLUE, ">> " + "marked: " + this.taskNumber);
    }
}
