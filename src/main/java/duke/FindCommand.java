package duke;

public class FindCommand extends Command {

    private String taskToFind;

    public FindCommand(String taskToFind) {
        this.taskToFind = taskToFind;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList matchingTasks = tasks.find(taskToFind);
        ui.showFilteredList(matchingTasks);
    }
}
