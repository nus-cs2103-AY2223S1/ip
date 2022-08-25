public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PikachuException;

    public abstract boolean isExit();
}
