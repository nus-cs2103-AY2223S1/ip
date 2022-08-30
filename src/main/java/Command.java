import java.io.IOException;
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui uI, FileOperations fileOperations) throws IOException;
}
