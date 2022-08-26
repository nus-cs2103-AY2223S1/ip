public class ListCommand extends Command {
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showNoTask();
        }
        for (int i = 1; i <= tasks.numOfTasks(); i++) {
            System.out.println(String.format("%d. %s", i, tasks.fetchTask(i).toString()));
        }
    }
}
