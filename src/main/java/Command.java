public abstract class Command {
    private boolean isBye;

    public Command(boolean isBye) {
        this.isBye = isBye;
    }

    public boolean isBye() {
        return isBye;
    }
    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws DukeException;
}
