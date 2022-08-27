public class NullCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        return;
    }
}
