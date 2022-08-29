public class AddEventCommand extends Command {
    public static final String KEYWORD = "event";
    private Event event;

    /**
     * Constructor method.
     *
     * @param event The event to be added
     */
    public AddEventCommand(Event event) {
        this.event = event;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds an Event task class to the task list.
     *
     * @param tasks The list of tasks
     * @param ui The UI of this application
     * @param storage The storage used for this application
     * @throws StashyException If any exception is caught
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        tasks.add(this.event);
        ui.showIndented("There, we have a new event:\n  " + tasks.get(tasks.size() - 1)
                + "\nYou have " + tasks.size() + " task(s) in the list.");
    }
}
