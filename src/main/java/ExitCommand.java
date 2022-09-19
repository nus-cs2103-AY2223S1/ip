import java.io.IOException;

public class ExitCommand extends Command {


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.saveTaskList(taskList);
        } catch (IOException e) {
            System.out.println("Error while saving the List");
        }
        ui.displayExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}