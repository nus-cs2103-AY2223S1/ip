package duke;

public abstract class Command {
    /**
     * Executes input command by user
     *
     * @param taskList TaskList object containing tasks input by user before
     * @param ui       Ui object that interacts with user
     * @param storage  Storage object that saves to and loads from storage file
     * @return String to be displayed to user after execution
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * @return boolean indicating if Duke program exits
     */
    public boolean isExit() {
        return false;
    }
}
