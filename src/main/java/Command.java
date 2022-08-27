import java.util.ArrayList;

public class Command {
    Task task;
    int index;
    boolean isExit = false;

    Command(int index) {
        this.index = index;
    }

    Command(Task task) {
        this.task = task;
    }

    Command() {
        
    }

    boolean isExit() {
        return this.isExit;
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {

    }
}
