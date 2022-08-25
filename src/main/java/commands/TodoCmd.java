package commands;

public class TodoCmd extends Command {
    private String body;

    public TodoCmd(String body) {
        this.body = body;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws TumuException {
        if (body.isBlank()) {
            throw new TodoException();
        } else {
            addTaskType(new Todo(body), tasks, ui);
        }
        saveUserTasks(storage, tasks);
    }
}
