public class ListCommand extends Command {
    void execute(TaskList tasks, Ui ui, Storage storage) {
        int len = tasks.getLength();
        ui.showOutput("You currently have " + len + " tasks:");
        ui.showOutput(tasks.toString());
    }

    public boolean isExit() {
        return false;
    }
}
