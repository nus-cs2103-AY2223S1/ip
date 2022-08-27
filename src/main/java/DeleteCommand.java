public class DeleteCommand extends Command {
    int index;
    String taskType;
    String userCommand;

    public DeleteCommand(String userCommand) {
        this.userCommand = userCommand;
        String[] taskData = userCommand.split(" ", 2);
        this.taskType = taskData[0].trim();
        this.index = Integer.parseInt(taskData[1].trim()) - 1;
    }
    public void execute (TaskList tasks, Ui ui, Storage storage) throws UwuException {
        if (index >= tasks.size()) {
            throw new NullTaskException("\n\thm...it seems that task " + String.valueOf(index) + " does not exist ><" +
                                        "\n\tplease check that you have keyed in the right task number~ <:");
        }
        Task task = tasks.remove(index);
        storage.save(tasks.taskListToStorageString());
        ui.deleteTask(task, tasks.size());
    };

    public boolean isExit(){
        return false;
    };
}
