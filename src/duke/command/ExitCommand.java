package duke;

public class ExitCommand extends Command{

    @Override
    public void execute(Ui ui, TaskList taskList) {
        toggleExit();
        ui.showBye();
    }
}