import java.io.IOException;

public class Unmark extends Command{

    private int taskNumber;

    public Unmark(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList taskList, Ui uI, FileOperations fileOperations) throws IOException {
        taskList.modifyTaskStatus(taskNumber, false);
        fileOperations.rewriteFile(taskList);
        uI.colouredPrint(uI.ANSI_BLUE, ">> " + "unmarked: " + this.taskNumber);
    }
}
