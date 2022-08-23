package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

public class DoneCommand extends Command{
    private final int indexToMarkAsDone;

    public DoneCommand(int indexToMarkAsDone) {
        this.indexToMarkAsDone = indexToMarkAsDone;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.markAsDone(this.indexToMarkAsDone);
        String message = "Nice! I've marked this task as done:\n\t\t" + taskList.taskToString(this.indexToMarkAsDone);
        ui.printWithDivider(message);
    }
}
