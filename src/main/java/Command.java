public abstract class Command {
    boolean isByeCommand = false;
    abstract void execute(TaskList taskList, Storage storage) throws DukeException;
}
