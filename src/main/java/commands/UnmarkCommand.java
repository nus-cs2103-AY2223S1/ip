public class UnmarkCommand extends Command {
    public static final String KEYWORD = "unmark";
    private int taskId;

    /**
     * Constructor method.
     *
     * @param taskId Task ID from the task list
     */
    public UnmarkCommand(Integer taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Unmarks a particular task from the given task list as not done.
     *
     * @param tasks The list of tasks
     * @param ui The UI of this application
     * @param storage The storage used for this application
     * @throws StashyException If any exception is caught
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        if (1 <= this.taskId && this.taskId <= tasks.size()) {
            ui.showIndented("L + ratio, unmarking this task as not done!");
            tasks.get(this.taskId - 1).unmarkAsNotDone();
            ui.showIndented("  " + tasks.get(this.taskId - 1));
        } else {
            throw new StashyException("Invalid task ID: " + this.taskId);
        }
    }
}
