public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String farewell = "Goodbye, hope to see you again soon!";
        ui.printMessage(farewell);
    }
}
