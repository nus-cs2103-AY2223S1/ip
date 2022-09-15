package duke;

/**
 * Represents a command to archive TaskList.
 * @author Tan Wen Cong
 */
public class ArchiveCommand extends Command {
    /**
     * Executes archive command by user
     *
     * @param taskList TaskList object containing tasks input by user before
     * @param ui       Ui object that interacts with user
     * @param storage  Storage object that saves to and loads from storage file
     * @return String to be displayed to user after execution
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.archive(taskList);
        return "Archived!";
    }
}
