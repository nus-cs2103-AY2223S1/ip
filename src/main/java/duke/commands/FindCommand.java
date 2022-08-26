package duke.commands;

import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Terminates the program.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds a task corresponding to the string.\n"
            + "\tEx. " + COMMAND_WORD;

    private final String taskSubstring;

    /**
     * Constructor for FindCommand.
     *
     * @param taskSubstring the substring to be searched for in the task list
     */
    public FindCommand(String taskSubstring) {
        this.taskSubstring = taskSubstring;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageFile storage) {
        taskList.findTask(taskSubstring);
    }

}