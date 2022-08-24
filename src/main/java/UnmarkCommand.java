public class UnmarkCommand extends Command {

    public UnmarkCommand(String content, TaskList tasks) {
        super(content, tasks);
    }

    @Override
    public boolean run() throws DukeException {
        try {
            Reply.printMessage(this.tasks.unmarkTask(Integer.parseInt(content) - 1));
        } catch (NumberFormatException e) {
            throw new DukeException("Task number need to be an integer!");
        }
        return false;
    }
}