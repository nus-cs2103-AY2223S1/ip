public abstract class Command {
    public abstract void execute(List tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
