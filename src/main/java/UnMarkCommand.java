public class UnMarkCommand extends Command {

    private final int index;

    public UnMarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        try {
            if (taskList.get(index).getDone()) {
                taskList.get(index).unmarkTask();
                ui.showUnmark(taskList, index);
            } else {
                throw new DukeException("Task is already unmarked");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Index %d does not exist on the list.", index + 1)); //plus 1 for indexing
        }
        new SaveCommand().execute(taskList, ui, storage);
    }

}
