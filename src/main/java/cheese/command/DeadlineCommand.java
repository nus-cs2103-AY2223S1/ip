package cheese.command;

import cheese.storage.Storage;
import cheese.ui.Ui;
import cheese.task.Deadline;
import cheese.task.Task;
import cheese.data.TaskList;

/**
 * Represents a user command to create a new deadline.
 */
public class DeadlineCommand extends Command {
  /** Description of new deadline. */
  private String description;

  /** Deadline of new deadline. */
  private String deadline;

  /**
   * Constructs an instance of <code>DeadlineCommand</code>.
   * 
   * @param description Description of new deadline.
   * @param deadline    Deadline of new deadline.
   */
  public DeadlineCommand(String description, String deadline) {
    this.description = description;
    this.deadline = deadline;
  }

  /**
   * Executes operations to create a new deadline, add deadline to list, and save
   * the list.
   * 
   * @param {@inheritDoc}
   */
  @Override
  public void execute(TaskList taskList, Storage storage, Ui ui) {
    Task addedTask = taskList.add(new Deadline(description, deadline));
    ui.showAddTask(addedTask, taskList.getSize());
    storage.save(taskList);
  }
}
