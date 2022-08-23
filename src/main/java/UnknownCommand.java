public class UnknownCommand extends Command {

    public UnknownCommand(String args) {
        super(args);
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws TedException {
        ui.showUnknownCommandError();
    }
}
