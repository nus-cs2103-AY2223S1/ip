import java.io.IOException;

public abstract class Command {
    private boolean end = false;

    public abstract void run(TaskList tasks, Ui ui, Storage storage) throws IOException;

    public void endApp() {
        this.end = true;
    }

    public boolean isEnd() {
        return this.end;
    }
}
