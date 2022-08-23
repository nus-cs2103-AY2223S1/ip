package commands;

import exception.*;
import tasks.*;
import main.*;

/**
 * DeadlineCommand which handles creation and message of DeadlineTask
 */
public class DeadlineTaskCommand extends TaskCommand {

  private String by;

  /**
   * Constructor for DeadlineTaskCommand which includes description of task and
   * when the task needed to be completed by
   * 
   * @param description Description of task
   * @param by          When the task is required by
   */
  public DeadlineTaskCommand(String description) throws DukeException {
    super(description);
    String[] eventlst = description.split("/by", 2);
    if (eventlst.length < 2 || eventlst[1].equals("")) {
      throw new DukeException("Alamak! Fill in when the deadline is by...");
    }
    this.description = eventlst[0];
    this.by = eventlst[1];
  }

  /**
   * Creates a new DeadlineTask object to tasklist and prints message to user
   */
  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    DeadlineTask task = new DeadlineTask(this.description, this.by);
    tasks.add(task);
    storage.save(tasks);
    super.printMessage(tasks, task);
  }
}
