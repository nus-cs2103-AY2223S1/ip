public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SallyException;
    public abstract boolean isExit();
}
