public abstract class Command {
    private boolean isExit = false;

    public abstract void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException;

    public void setExit() {
        this.isExit = true;
    }

    public boolean getExit() {
        return this.isExit;
    }
}
