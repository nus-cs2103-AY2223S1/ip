public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    void execute(TaskList tasklist, Ui ui, Storage storage) {
        String type = this.task.getTaskType();
        switch (type) {
            case ("Todo"):
                tasklist.todoTask(this.task.description, storage);
                break;
            case ("Deadline"):
                tasklist.deadlineTask(this.task.description, ((Deadline) this.task).by, storage);
                break;
            case ("Event"):
                tasklist.eventTask(this.task.description, ((Event) this.task).at, storage);
                break;
        }
    }
}