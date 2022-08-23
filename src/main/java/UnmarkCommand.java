public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(String args) throws TedException {
        super(args);
        try {
            this.index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new TedException("The number of task to be unmarked passed must be a number.");
        }
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws TedException {
        try {
            if (tasks.size() == 0) {
                throw new TedException("There is no tasks here. Feel free to add a task.");
            }

            if (index <= 0) {
                throw new TedException("The number of task to be unmarked must be greater than 0.");
            }

            if (index > tasks.size()) {
                throw new TedException(
                        String.format(
                                "The number of task to be unmarked must be less than or equal to %d.",
                                tasks.size()
                        )
                );
            }

            tasks.get(index - 1).unmark();
        } catch (NumberFormatException e) {
            throw new TedException("The number of task to be unmarked passed must be a number.");
        }

        try {
            storage.saveTasks(tasks);
        } catch (Exception e) {
            ui.showTaskSavingError(e);
        }
    }
}
