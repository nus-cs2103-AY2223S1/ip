public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeCommandNotFoundException {
        throw new DukeCommandNotFoundException();
    }
}
