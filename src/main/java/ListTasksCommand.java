public class ListTasksCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        Ui.prettyPrint(tasks.toString());
    }
}
