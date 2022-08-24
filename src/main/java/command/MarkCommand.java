package command;

import java.util.ArrayList;

import task.Task;
import ui.Ui;

public class MarkCommand extends Command {

  /**
   * Initialises a MarkCommand.
   *
   * @param commandArgs An array of Strings containing information
   * pertaining to this specific mark command.
   * @param tasks An <code>ArrayList<Task></code>, containing the
   * current existing tasks in the programme.
   */
  public MarkCommand(String[] commandArgs, ArrayList<Task> tasks) {
    super(commandArgs, tasks);
  }

  /**
   * Marks or unmarks a task to denote its completion.
   *
   * @return Returns true for the main Duke class to know to
   * continue asking for input.
   */
  @Override
  public boolean performAction() {
    if (this.commandArgs[0].equals("mark")) {
      Integer index = Integer.parseInt(this.commandArgs[1]) - 1;
      Task taskToUpdate = this.tasks.get(index);
      Ui.print(taskToUpdate.updateStatus(true) + "\n");

    } else if (this.commandArgs[0].equals("unmark")) {
      Integer index = Integer.parseInt(this.commandArgs[1]) - 1;
      Task taskToUpdate = this.tasks.get(index);
      Ui.print(taskToUpdate.updateStatus(false) + "\n");
    }

    return true;
  }
}
