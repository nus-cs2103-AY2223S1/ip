public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index= index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(index);
        String taskInString = task.toString();
        if (!task.isDone) {
            task.markAsDone();
            String markTask = task.toString();
            ui.showMarked(markTask);
            try {
                storage.savesFile(tasks);
            } catch (SallyException e) {
                System.out.println("File Not Found");
            }
        } else {
            ui.showPreviouslyMarked(taskInString);
        }
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
