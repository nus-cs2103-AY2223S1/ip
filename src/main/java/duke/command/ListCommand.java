public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("\t Here are the tasks in your list:");
        tasks.listTasks();
    }
}
