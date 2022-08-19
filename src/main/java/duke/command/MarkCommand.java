package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class MarkCommand extends Command {
    public static final String COMMAND_ID = "MARK";
    private final int targetIndex;

    /**
     * Constructor for MarkCommand which inherits Command
     * @param targetIndex number on the list to be deleted
     * @return an instance of MarkCommand
     * @see Command
     */
    public MarkCommand(int targetIndex) {
        super();
        this.targetIndex = targetIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.markTaskAsDone(this.targetIndex);
        storage.writeDataToFile(taskList);
    }
}
