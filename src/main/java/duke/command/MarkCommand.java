package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.Ui;
import duke.task.TaskList;

public class MarkCommand extends Command {
    public static final String COMMAND_ID = "MARK";
    private final int targetIndex;

    /**
     * Constructs an instance of MarkCommand which inherits Command
     * @param targetIndex number on the list to be deleted
     * @return an instance of MarkCommand
     * @see Command
     */
    public MarkCommand(int targetIndex) {
        super();
        this.targetIndex = targetIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        String result = taskList.markTaskAsDone(this.targetIndex);
        storage.writeDataToFile(taskList);
        return result;
    }
}
