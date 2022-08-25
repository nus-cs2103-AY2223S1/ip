public abstract class AddCommand extends Command {
    protected Task task;

    public AddCommand(String command, Task task) {
        super(command);
        this.task = task;
    }

    // take in storage and ui
    public void execute() throws IllegalArgumentException {
        // call storage to add the task in (into both list of commands and current list of tasks)
    }
}
