import java.util.ArrayList;

public abstract class Command {
    private boolean isEnder;

    protected ArrayList<String> rest;

    protected Command(boolean isEnder, ArrayList<String> rest) {
        this.isEnder = isEnder;
        this.rest = rest;
    }

    public abstract void execute(TaskList tl, Ui ui, Storage storage);

    public boolean isExit() {
        return this.isEnder;
    }
}
