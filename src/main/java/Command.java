public abstract class Command {
    public boolean isTerminator;

    protected Command(Boolean isTerminator) {
        this.isTerminator =  isTerminator;
    }

    public abstract void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException;

}
