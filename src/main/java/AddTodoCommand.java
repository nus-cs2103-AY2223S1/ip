public class AddTodoCommand extends Command {

    private String commandDetails;

    public AddTodoCommand(String commandDetails) {
        this.commandDetails = commandDetails;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            String description = Parser.parseTodoDetails(commandDetails);
            Task newTask = new ToDo(description);
            tasks.addTask(newTask);
            ui.printAddedTask(newTask, tasks);
            storage.updateDisk(tasks);
        } catch (PonyException e) {
            System.out.println(e.getMessage());
        }
    }
}

