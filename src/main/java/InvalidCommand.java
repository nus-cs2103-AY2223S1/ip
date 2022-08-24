public class InvalidCommand extends Command{


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(new DukeException("Sorry. I don't understand your command!!!").getMessage());
    }
}
