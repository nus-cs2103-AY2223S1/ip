package commands;

import exception.*;
import java.util.ArrayList;
import tasks.*;

/**
 * AddCommand class is the parent class for Todo, Deadline and Event
 */
public class AddCommand extends Command {

  protected String description;

  public AddCommand(String description) {
    this.description = description;
  }

  @Override
  public void execute(ArrayList<Task> tasklist) throws DukeException {}

  /**
   * Prints the correct message depending on the tasks type
   * @param tasklist Array of tasks
   * @param task The task that has been added
   */
  public void printMessage(ArrayList<Task> tasklist, Task task) {
    System.out.println("Got it. I've added this task: ");
    System.out.println(task.toString());
    System.out.println(
      "Now you have " + tasklist.size() + " task(s) in the list"
    );
  }
}
