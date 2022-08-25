public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, ListLoader storage) {
        int tasksLeft = tasks.tasksLeft();
        ui.showList(tasks.giveList(), tasksLeft);
    }
}
