public abstract class UpdateCommand extends Command {
    protected Task task;

    public UpdateCommand(String command, Task task) {
        super(command);
        this.task = task;
    }
}
