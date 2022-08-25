public class DeadlineCommand extends Command {
  private String description;
  private String deadline;

  public DeadlineCommand(String description, String deadline) {
    this.description = description;
    this.deadline = deadline;
  }

  @Override
  public void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException {
    Task addedTask = taskList.add(new Deadline(description, deadline));
    ui.showAddTask(addedTask, taskList.getSize());
    storage.save(taskList);
  }
}
