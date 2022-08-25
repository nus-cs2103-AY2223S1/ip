public class DeleteCommand extends Command {
    private int pos;
    
    public DeleteCommand(int pos) {
        this.pos = pos;
    }
    public void execute(TaskList tasks) throws DukeException {
        tasks.deleteTask(pos);
    }
}
