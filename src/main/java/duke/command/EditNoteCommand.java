package duke.command;

import duke.Ui;
import duke.task.TaskList;

public class EditNoteCommand extends Command {
    private int taskIndex;
    private String newNote;

    public EditNoteCommand(int taskIndex, String newNote) {
        this.taskIndex = taskIndex;
        this.newNote = newNote;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) {
        taskList.editNote(this.taskIndex, this.newNote);
        return ui.printEditNote(this.taskIndex, this.newNote);
    }
}
