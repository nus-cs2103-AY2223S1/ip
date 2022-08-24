package command;

import java.util.ArrayList;

import task.Task;
import ui.Ui;

public class ListCommand extends Command {

  public ListCommand(String[] commandArgs, ArrayList<Task> tasks) {
    super(commandArgs, tasks);
  }

  @Override
  public boolean performAction() {
    Ui.print("Sweetie, here is the list of tasks that you have <3");
    for (int i = 0; i < this.tasks.size(); i++) {
      Ui.print(
          (i + 1)
          + ". " 
          + this.tasks.get(i));
    }

    System.out.println("");
    return true;
  }
}
