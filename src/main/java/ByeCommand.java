public class ByeCommand extends Command {
    @Override
    public void run(TaskList taskList) throws DukeException {
        Ui.bye();
        System.exit(0);
    }
}
