package commands;

import exception.*;
import java.util.ArrayList;
import tasks.*;

/**
 * DeadlineCommand which handles creation and message of Deadline
 */
public class DeadlineCommand extends AddCommand {

  private String by;

  /**
   * Constructor for DeadlineCommand which includes description of task and when the task needed to be completed by
   * @param description Description of task
   * @param by When the task is required by
   */
  public DeadlineCommand(String description, String by) {
    super(description);
    this.by = by;
  }

  /**
   * Creates a new Deadline object to tasklist and prints message to user
   */
  @Override
  public void execute(ArrayList<Task> tasklist) throws DukeException {
    Deadline task = new Deadline(this.description, this.by);
    tasklist.add(task);
    super.printMessage(tasklist, task);
  }
}
