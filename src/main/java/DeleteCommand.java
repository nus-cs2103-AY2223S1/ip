public class DeleteCommand extends Command {
    private String taskNum;
    
    public DeleteCommand(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int taskNumber = Integer.parseInt(this.taskNum) - 1;
            Task deletedTask = tasks.delete(taskNumber);
            ui.sayTaskDeleted(deletedTask, tasks.size());
        } catch (IanaException e) {
            ui.echo(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            ui.echo("This task number does not exist! ^^");
        } catch (NumberFormatException e) {
            ui.echo("Oops! Delete a task number instead <[u_u]>");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}