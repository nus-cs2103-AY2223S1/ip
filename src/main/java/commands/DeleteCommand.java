package commands;

import tasks.Task;
import java.util.ArrayList;

public class DeleteCommand extends Command {

  public DeleteCommand(String[] commandArgs, ArrayList<Task> tasks) {
    super(commandArgs, tasks);
  }

  @Override
  public boolean performAction() {
    Integer index = Integer.parseInt(this.commandArgs[1]) - 1;
    Task taskToRemove = this.tasks.get(index);
    this.tasks.remove(index);
    System.out.println(
        "I've successfully removed this task: \n" +
        taskToRemove +
        "\n" + 
        "Do your own chores next time hunbun!"
        );
    return true;
  }
}
