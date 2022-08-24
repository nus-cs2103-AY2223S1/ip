public abstract class Command {

    abstract void execute(TasksController controller, Ui ui, Storage storage);
}
