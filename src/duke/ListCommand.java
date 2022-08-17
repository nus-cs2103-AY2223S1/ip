package duke;

public class ListCommand extends Command{

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showList(taskList);
    }
}
