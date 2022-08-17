package commands;

import exception.*;
import java.util.ArrayList;
import tasks.*;

/**
 * Event command has an at field for the timing of the event
 */
public class EventCommand extends AddCommand {

  protected String at;

  /**
   * Constructor for EventCommand with at field
   * @param description Description of event
   * @param at When the event is at
   */
  public EventCommand(String description) throws DukeException {
    String[] eventlst = description.split("/at", 2);
    if (eventlst.length < 2) {
      throw new DukeException("Alamak! Fill in when the event is at...");
    }
    this.description = eventlst[0];
    this.at = eventlst[1];
  }

  /**
   * Creates new Event and add to tasklist as well as print message to user
   */
  @Override
  public void execute(ArrayList<Task> tasklist) throws DukeException {
    Event task = new Event(this.description, this.at);
    tasklist.add(task);
    super.printMessage(tasklist, task);
  }
}
