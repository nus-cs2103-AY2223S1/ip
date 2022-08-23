public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String args) throws TedException {
        super(args);
        if (args.isEmpty()) {
            throw new TedException("The description of todo must not be empty.");
        }

        this.description = args;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(new ToDo(description));
        ui.showAddedTaskSuccess(tasks);

        try {
            storage.saveTasks(tasks);
        } catch (Exception e) {
            ui.showTaskSavingError(e);
        }
    }
}
