public class EventCommand extends Command {
  private String description;
  private String timeInterval;

  public EventCommand(String description, String timeInterval) {
    this.description = description;
    this.timeInterval = timeInterval;
  }

  @Override
  public void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException {
    Task addedTask = taskList.add(new Event(description, timeInterval));
    ui.showAddTask(addedTask, taskList.getSize());
    storage.save(taskList);
  }
}
