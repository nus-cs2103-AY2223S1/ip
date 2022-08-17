package commands;

import exception.*;
import java.util.ArrayList;
import tasks.*;

public class AddCommand extends Command {

  protected String description;

  public AddCommand(String description) {
    this.description = description;
  }

  @Override
  public void execute(ArrayList<Task> tasklist) throws DukeException {}

  public void printMessage(ArrayList<Task> tasklist, Task task) {
    System.out.println("Got it. I've added this task: ");
    System.out.println(task.toString());
    System.out.println(
      "Now you have " + tasklist.size() + " task(s) in the list"
    );
  }
}
