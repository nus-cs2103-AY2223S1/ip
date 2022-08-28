package duke;

public class UnmarkCommand extends TaskListCommand {
    UnmarkCommand(String cmd) {
        super(cmd);
    }

    @Override
    void specialisedFunction(TaskList tasks, Ui ui, Storage storage, int taskIndex) {
        boolean isUnchecked = tasks.fetchTask(taskIndex).uncheck();
        if (isUnchecked) {
            ui.showUnmarked();
        } else {
            ui.showAlreadyUnmarked();
        }
        System.out.println(tasks.fetchTask(taskIndex));
    }
}
