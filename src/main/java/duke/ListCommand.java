package duke;

public class ListCommand extends Command {
    public ListCommand() {

    }

    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTasks(taskList);
        return taskList;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
