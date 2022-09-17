package duke.command;

import duke.oop.Storage;
import duke.task.Task;
import duke.oop.TaskList;
import duke.oop.Ui;

public class DeleteCommand extends Command{
    private int indexToDelete;

    public DeleteCommand(int inputNumber) {
        this.indexToDelete = inputNumber - 1;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        Task deleteClass = taskList.getTasks().get(indexToDelete);
        taskList.getTasks().remove(indexToDelete);
        storage.update(taskList.getTasks());
        return "Haha! I've deleted this command as done:\n" + deleteClass.toString();
    }
}
