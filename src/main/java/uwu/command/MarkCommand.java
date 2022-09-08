package uwu.command;

import uwu.exception.NullTaskException;
import uwu.exception.UwuException;
import uwu.task.Task;
import uwu.task.TaskList;
import uwu.uwu.Storage;
import uwu.uwu.Ui;

/**
 * Marks or unmarks a task in the task list.
 */
public class MarkCommand extends Command {
    /** The index of the task to be marked or unmarked. */
    private int index;

    /** The user command parsed into MarkCommand. */
    private String userCommand;

    /** The type of command, mark or unmark. */
    private String taskType;

    /**
     * Constructor for a MarkCommand object.
     *
     * @param userCommand The command the user typed.
     */
    public MarkCommand(String userCommand) {
        this.userCommand = userCommand;
        String[] taskData = userCommand.split(" ", 2);
        this.taskType = taskData[0];
        this.index = Integer.parseInt(taskData[1].trim()) - 1;
    }

    /**
     * Executes the MarkCommand which marks or unmarks the task at the
     * specified index.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out UwuBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     * @throws UwuException If task index is out of bounds.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws UwuException {
        if (index >= tasks.size()) {
            throw new NullTaskException("hm...it seems that task " + String.valueOf(index + 1) + " does not exist ><"
                    + "\nplease check that you have keyed in the right task number~ <:");
        }

        String response = "oops something went wrong while marking your task TT";

        switch (taskType) {
        case "mark":
            Task markedTask = tasks.get(index);
            markedTask.setIsDone(true);
            storage.save(tasks.taskListToStorageString());
            response = ui.markTask(markedTask);
            break;
        case "unmark":
            Task unmarkedTask = tasks.get(index);
            unmarkedTask.setIsDone(false);
            storage.save(tasks.taskListToStorageString());
            response = ui.unmarkTask(unmarkedTask);
            break;
        default:
            throw new NullTaskException("\toops something went wrong!");
        }

        return response;
    }

    /**
     * Returns whether MarkCommand exits the program.
     *
     * @return false as MarkCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
