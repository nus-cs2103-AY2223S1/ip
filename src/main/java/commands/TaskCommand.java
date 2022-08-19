package commands;

import java.util.ArrayList;
import tasks.Task;
import exceptions.DukeException;

public class TaskCommand extends Command {
  
  public TaskCommand(String[] commandArgs, ArrayList<Task> tasks) {
    super(commandArgs, tasks);
  }

  @Override
  public boolean performAction() {

    if (this.commandArgs[0].equals("todo")) {
      tasks.add(new Task(this.commandArgs[1]));

    } else if (this.commandArgs[0].equals("deadline")) {
      tasks.add(new Task(this.commandArgs[1], this.commandArgs[2], Task.Type.DEADLINE));

    } else if (this.commandArgs[0].equals("event")) {
      tasks.add(new Task(this.commandArgs[1], this.commandArgs[2], Task.Type.EVENT));
    }
  System.out.println("Hey sweetie, I've added: '" + this.commandArgs[1] + "' to your lists of tasks~\n");
  return true;
  }
}
