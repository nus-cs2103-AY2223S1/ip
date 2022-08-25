public class DeleteCommand extends Command {
  private int taskIndex;

  public DeleteCommand(int taskIndex) {
    this.taskIndex = taskIndex;
  }

  @Override
  public void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException {
    Task deletedTask = taskList.delete(taskIndex);
    ui.showDeleteTask(deletedTask, taskList.getSize());
    storage.save(taskList);
  }
}
