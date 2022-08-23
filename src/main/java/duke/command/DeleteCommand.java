package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;

public class DeleteCommand extends Command{
    private final int index;

    public DeleteCommand(int index){
        this.index = index;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList taskList, UI ui, Storage storage) {
        Task toRemove = taskList.remove(this.index);
        String message = "Noted. I've removed this task:\n\t\t" + toRemove + "\n\tNow you have "
                + taskList.getSize() + " tasks in the list.";
        ui.printWithDivider(message);
    }
}