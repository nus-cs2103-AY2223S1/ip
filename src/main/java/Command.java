public abstract class Command {
    boolean isExit = false;

    public boolean isExit(){
        return this.isExit;
    }

    abstract void execute(TaskList tasklist, Ui ui, Storage storage);
}
