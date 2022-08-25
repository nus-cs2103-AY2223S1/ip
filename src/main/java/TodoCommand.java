public class TodoCommand extends Command {
  private String description;

  public TodoCommand(String description) {
    this.description = description;
  }

  @Override
  public void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException {
    Task addedTask = taskList.add(new Todo(description));
    ui.showAddTask(addedTask, taskList.getSize());
    storage.save(taskList);
  }
}
