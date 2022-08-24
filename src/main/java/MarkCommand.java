 public class MarkCommand extends Command {

    public MarkCommand(String content, TaskList tasks) {
        super(content, tasks);
    }

    @Override
    public String run() throws DukeException {
        String reply;
        try {
            reply = this.tasks.markTask(Integer.parseInt(this.content) - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("Task number need to be an integer!");
        }
        return reply;
    }
}