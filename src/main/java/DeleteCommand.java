public class DeleteCommand extends Command {

    public DeleteCommand(String content, TaskList tasks) {
        super(content, tasks);
    }

    @Override
    public String run() throws DukeException {
        String reply;
        try {
            reply = this.tasks.deleteTask(Integer.parseInt(this.content) - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("Task number need to be an integer!");
        }
        return reply;
    }
}