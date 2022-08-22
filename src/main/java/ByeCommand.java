public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks,Ui ui,Storage storage) {
        ui.showBye();
        this.setExit();
    }
}
