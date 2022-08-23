package commands;

import exception.*;
import tasks.*;
import main.*;

/**
 * EventTaskCommand has an at field for the timing of the event
 */
public class EventTaskCommand extends TaskCommand {

  protected String at;

  /**
   * Constructor for EventTaskCommand with at field
   * 
   * @param description Description of event
   * @param at          When the event is at
   */
  public EventTaskCommand(String description) throws DukeException {
    super(description);
    String[] eventlst = description.split("/at", 2);
    if (eventlst.length < 2 || eventlst[1].equals("")) {
      throw new DukeException("Alamak! Fill in when the event is at...");
    }
    this.description = eventlst[0];
    this.at = eventlst[1];
  }

  /**
   * Creates new EventTask and add to tasklist as well as print message to user
   */
  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    EventTask task = new EventTask(this.description, this.at);
    tasks.add(task);
    storage.save(tasks);
    super.printMessage(tasks, task);
  }
}
