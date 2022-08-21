public class AddCommand extends Command{
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.task);
        ui.printMessage("\tadded / ajouté:"
                        + "\n\t\t" + this.task.toString());
        ui.printMessage("\tYou now have " + Task.numberOfTasks + " task(s)!"
                        + "\n\tVous avez " + Task.numberOfTasks + " tâche(s)!");
    }
}
