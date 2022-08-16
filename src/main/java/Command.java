import java.util.ArrayList;

abstract class Command {
    private String cmd;

    public Command (String cmd) {
        this.cmd = cmd;
    }

    public String getCmd () {
        return this.cmd;
    }

    abstract void execute (String str, ArrayList<Task> tasks) throws DukeException;
}
