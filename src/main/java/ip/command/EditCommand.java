package ip.command;

import java.util.NoSuchElementException;
import java.util.Scanner;

import ip.exception.IndexNotSpecified;
import ip.exception.NoTaskFound;
import ip.utility.Storage;
import ip.utility.TaskList;

/**
 * DukeCommand to edit a task in the task list.
 */
public class EditCommand extends DukeCommand {
    /** DukeCommand given by the user */
    private final String editType;
    /** Options following the command given */
    private final Scanner options;

    /**
     * Constructor for EditCommand.
     *
     * @param editType Type of edit command given.
     * @param options Options to be used to edit tasks.
     */
    public EditCommand(String editType, Scanner options) {
        this.editType = editType;
        this.options = options;
    }

    /**
     * Execute the given command to the specified task.
     *
     * @param taskList The task list to search the task for.
     * @param storage The storage to save and backup to.
     * @throws IndexNotSpecified If no index number was specified.
     * @throws NoTaskFound       If there is no task at the index number specified.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws IndexNotSpecified, NoTaskFound {
        assert !editType.isEmpty() : "No edit type specified.";
        storage.saveToBackup(taskList);
        int taskIndex;
        try {
            taskIndex = options.nextInt();
        } catch (NoSuchElementException e) {
            throw new IndexNotSpecified();
        }
        switch (editType) {
        case "mark":
            taskList.mark(taskIndex - 1);
            break;
        case "unmark":
            taskList.unmark(taskIndex - 1);
            break;
        case "delete":
            taskList.delete(taskIndex - 1);
            break;
        default:
            return "Cannot edit task with this edit type.";
        }
        storage.saveToLatest(taskList);
        return "Task successfully edited.";
    }
}
