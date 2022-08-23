public abstract class Command {

    protected String args;

    public Command(String args) {
        this.args = args;
    }

    abstract public void run(TaskList tasks, Ui ui, Storage storage) throws TedException;

    public boolean isExit() {
        return false;
    }
}
