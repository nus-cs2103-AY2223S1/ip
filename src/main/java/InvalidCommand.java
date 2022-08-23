public class InvalidCommand extends Command {
    public InvalidCommand(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    private final String errorMsg;

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(errorMsg);
    }
}
