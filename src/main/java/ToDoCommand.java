public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.printAddTask(taskList.addToDo(this.description));
        ui.printSizeOfList(taskList.size());
        storage.saveLocalData(taskList.stringify());
    }
}
