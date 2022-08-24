package command;

import java.util.ArrayList;

import task.Task;
import ui.Ui;

public class DeleteCommand extends Command {

  public DeleteCommand(String[] commandArgs, ArrayList<Task> tasks) {
    super(commandArgs, tasks);
  }

  @Override
  public boolean performAction() {
    Integer index = Integer.parseInt(this.commandArgs[1]) - 1;
    Task taskToRemove = tasks.get(index);
    tasks.remove(index.intValue());
    Ui.print(
        "I've successfully removed this task:\n" +
        taskToRemove +
        "\n\n" +
        "Do your own chores next time hunbun!"
        );
    return true;
  }
}
