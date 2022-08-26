public class ByeCommand extends Command {
    public ByeCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList) {
        super.isExit = true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        this.execute(taskList);
        ui.printWithDivider("Bye. Hope to see you again soon!");
    }
}
