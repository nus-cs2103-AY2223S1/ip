package Command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command{
    private int indexToDelete;

    public DeleteCommand(int inputNumber) {
        this.indexToDelete = inputNumber - 1;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        Task deleteClass = taskList.getTasks().get(indexToDelete);
        taskList.getTasks().remove(indexToDelete);
        return "Haha! I've deleted this command as done:\n" + deleteClass.toString();
    }
}
