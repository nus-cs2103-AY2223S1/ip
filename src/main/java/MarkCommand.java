public class MarkCommand extends Command {
    String[] str;
    boolean mark;

    public MarkCommand(String[] str, boolean mark) {
        this.str = str;
        this.mark = mark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = 0;
        Task myTask;

        try {
            index = Integer.parseInt(str[1]) - 1;
        } catch (Exception e) {
            ui.emptyDescription();
        }

        if (index > tasks.size() || index < 0) {
            ui.invalidTask();
        }

        myTask = tasks.get(index);
        if (mark) {
            myTask.markAsDone();
            ui.complete(myTask);
        } else {
            myTask.markAsUndone();
            ui.incomplete(myTask);
        }
        storage.writeFile(tasks);


    }
}
