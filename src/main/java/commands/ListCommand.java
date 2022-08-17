package commands;

import exception.*;
import java.util.ArrayList;
import tasks.*;

public class ListCommand extends Command {

  /**
   * Lists all tasks in tasklist or print default message if no tasks found
   */
  @Override
  public void execute(ArrayList<Task> tasklist) throws DukeException {
    if (tasklist.size() == 0) {
      System.out.println("No task found so far.");
    } else {
      System.out.println("Here are the tasks in your list:");
      for (int i = 0; i < tasklist.size(); i++) {
        System.out.println((i + 1) + ". " + tasklist.get(i).toString());
      }
    }
  }
}
