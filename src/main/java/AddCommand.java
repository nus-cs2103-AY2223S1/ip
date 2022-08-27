public class AddCommand extends Command { //Creating a ToDo Task and adding to the taskList
    String description;
    boolean isDone;

    public AddCommand(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        Task todo = new ToDo(description);
        taskList.addTask(todo);
        storage.saveData(taskList);
        UI.added(todo);
    }

}
