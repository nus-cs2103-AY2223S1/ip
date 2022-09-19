import java.io.IOException;

public class UnmarkCommand extends Command {

    protected int index;
    private final String MESSAGE =  "\tNoted! I have marked " +
            "the task as not done yet:";

    UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.unmarkTask(index);
        try {
            storage.saveTaskList(taskList);
        } catch (IOException e) {
            System.out.println("Error while saving the text");
        }
        String text = MESSAGE + "\n " + taskList.getTaskString(index);
        ui.displayMessage(text);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}