public class AddCommand extends Command{
    private String fullCommand;
    public AddCommand(String fullCommand){
        this.fullCommand = fullCommand;
    }
    @Override
    public void execute(TaskList taskList, Storage
                         storage) throws DukeException{
        taskList.addTask(Task.createATask(fullCommand));
        storage.updateFile(taskList.getTaskList());
    }
}
