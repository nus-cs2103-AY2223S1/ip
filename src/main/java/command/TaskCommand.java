package command;

import java.util.ArrayList;

import exception.DukeException;
import task.Task;
import ui.Ui;

public class TaskCommand extends Command {
  
  /**
   * Initialises a TaskCommand.
   *
   * @param commandArgs An array of Strings containing information
   * pertaining to this specific mark command.
   * @param tasks An <code>ArrayList<Task></code>, containing the
   * current existing tasks in the programme.
   */
  public TaskCommand(String[] commandArgs, ArrayList<Task> tasks) {
    super(commandArgs, tasks);
  }

  /**
   * Adds this task to the current list of tasks.
   *
   * @return Returns true for the main Duke class to know to
   * continue asking for input.
   */
  @Override
  public boolean performAction() {

    if (this.commandArgs[0].equals("todo")) {
      tasks.add(new Task(this.commandArgs[1]));

    } else if (this.commandArgs[0].equals("deadline")) {
      tasks.add(new Task(this.commandArgs[1], this.commandArgs[2], Task.Type.DEADLINE));

    } else if (this.commandArgs[0].equals("event")) {
      tasks.add(new Task(this.commandArgs[1], this.commandArgs[2], Task.Type.EVENT));
    }
  Ui.print("Hey sweetie, I've added: '" + this.commandArgs[1] + "' to your lists of tasks~");
  return true;
  }
}
