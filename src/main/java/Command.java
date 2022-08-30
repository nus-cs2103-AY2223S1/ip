public abstract class Command {

    public abstract void execute(TaskList tasks);

    public abstract boolean isExit();
}
