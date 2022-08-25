public class UnmarkCommand extends Command {
  private int taskIndex;

  public UnmarkCommand(int taskIndex) {
    this.taskIndex = taskIndex;
  }

  @Override
  public void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException {
    Task taskNotDone = taskList.markTaskAsNotDone(taskIndex);
    ui.showMarkTaskAsNotDone(taskNotDone);
    storage.save(taskList);
  }
}
