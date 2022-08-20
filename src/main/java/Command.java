import java.util.ArrayList;

public abstract class Command {
    protected ArrayList<Task> tasks;

    protected void setData(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public abstract CommandResult execute() throws DukeException;
}
