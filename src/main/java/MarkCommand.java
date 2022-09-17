public class MarkCommand extends Command{
    private String commandDetails;
    public MarkCommand(String commandDetails) {
        this.commandDetails = commandDetails;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            int taskIndex = Parser.parseTaskIndex(commandDetails);
            Task task = tasks.getTask(taskIndex - 1);
            task.markAsDone();
            ui.printMarkedTask(task);
            storage.updateDisk(tasks);
        } catch (PonyException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(":( OOPS!!! Please provide the correct details!!");
        }
    }
}
