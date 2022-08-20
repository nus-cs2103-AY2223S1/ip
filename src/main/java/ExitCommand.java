public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return;
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
