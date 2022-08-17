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
  public EventCommand(String description, String at) {
    super(description);
    this.at = at;
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
