package duke;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye nya! Hope to see you again nya.";
    }
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "Bye nya! Hope to see you again nya.";
    }
}
