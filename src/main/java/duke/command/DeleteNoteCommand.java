package duke.command;

import duke.Ui;
import duke.task.TaskList;

public class DeleteNoteCommand extends Command{
    private int taskIndex;

    public DeleteNoteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) {
        taskList.deleteNote(this.taskIndex);
        return ui.printDeleteNote(this.taskIndex);
    }
}
