public class TodoCommand extends Command{

    private String description;

    public TodoCommand(String description) {
            this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Todo todo = new Todo(this.description);
        taskList.addTask(todo);
        ui.printMessage("+ Added this todo:\n" + todo + "\nNow you have " + taskList.listSize()
                + " tasks in the list\n");
        //storage.saveTasks(taskList);

    }

    @Override
    public boolean isExit(){
        return false;
    }
}
