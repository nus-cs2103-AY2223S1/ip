public interface Command {
    public void execute(TaskList tasks, Storage storage) throws DukeException;
}
