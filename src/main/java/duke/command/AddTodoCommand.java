package duke.command;

public class AddTodoCommand extends Command {
    String taskName;

    public AddTodoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task todo = new Todo(taskName);
        taskList.addTasks(todo);
        storage.saveTasks(taskList);
        ui.repeater(todo + " added!");
    }

}
