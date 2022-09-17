public class DeleteCommand extends Command{
    private String commandDetails;
    public DeleteCommand(String commandDetails) {
        this.commandDetails = commandDetails;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            int taskIndex = Parser.parseTaskIndex(commandDetails);
            Task task = tasks.getTask(taskIndex - 1);
            tasks.deleteTask(taskIndex - 1);
            ui.printDeletedTask(task, tasks);
            storage.updateDisk(tasks);
        } catch (PonyException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(":( OOPS!!! Please provide the correct details!!");
        }
    }
}
