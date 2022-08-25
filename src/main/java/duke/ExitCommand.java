package duke;

public class ExitCommand extends Command {
    /**
     * Exits Duke program
     *
     * @param taskList TaskList object containing tasks input by user before
     * @param ui       Ui object that interacts with user
     * @param storage  Storage object that saves to and loads from storage file
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Returns true to indicate Duke program to exit
     *
     * @return boolean indicating Duke program to exit
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
