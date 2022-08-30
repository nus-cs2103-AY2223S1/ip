import java.io.IOException;

public class Delete extends Command{
    private int taskNumber;

    public Delete(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList taskList, Ui uI, FileOperations fileOperations) throws IOException {
        taskList.deleteTask(taskNumber);
        fileOperations.rewriteFile(taskList);
        uI.colouredPrint(uI.ANSI_BLUE, ">> " + "deleted: Task " + this.taskNumber);
    }
}
