public class MarkCommand extends Command {

    private final int index;

    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        try {
            if (!taskList.get(index).getDone()) {
                taskList.get(index).markTask();
                ui.showMark(taskList, index);
            } else {
                throw new DukeException("Task is already marked");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Index %d does not exist on the list.", index + 1)); //plus 1 for indexing
        }
        new SaveCommand().execute(taskList, ui, storage);
    }
}
