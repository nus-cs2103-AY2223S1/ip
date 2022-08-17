package commands;

import java.util.ArrayList;
import tasks.Task;

public class ListCommand extends Command {

  public ListCommand(String[] commandArgs, ArrayList<Task> tasks) {
    super(commandArgs, tasks);
  }

  @Override
  public boolean performAction() {
    for (int i = 0; i < this.tasks.size(); i++) {
      System.out.println((i + 1) + ". " + this.tasks.get(i));
    }

    System.out.println("");
    return true;
  }
}
