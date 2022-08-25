public class UnknownCommand extends Command{

    @Override
    public void run(TaskList taskList) throws DukeException {
        throw new DukeException("I don't know what you are telling me to do");
    }
}
