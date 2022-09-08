import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DrakeException;

    public boolean isExit() {
        return false;
    }
}
