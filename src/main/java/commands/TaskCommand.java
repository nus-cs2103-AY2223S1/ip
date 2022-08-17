package commands;

import java.util.ArrayList;
import tasks.*;

public class TaskCommand extends Command {
  
  public TaskCommand(String[] commandArgs, ArrayList<Task> tasks) {
    super(commandArgs, tasks);
  }

  @Override
  public boolean performAction() {
    if (this.commandArgs[0].equals("todo")) {
      tasks.add(new Todo(this.commandArgs[1]));

    } else if (this.commandArgs[0].equals("deadline")) {
      tasks.add(new Deadline(this.commandArgs[1], this.commandArgs[2]));

    } else if (this.commandArgs[0].equals("event")) {
      tasks.add(new Event(this.commandArgs[1], this.commandArgs[2]));
    } // TODO: else throw error
  System.out.println("Hey sweetie, I've added: '" + this.commandArgs[1] + "' to your lists of tasks~\n");
  return true;
  }
}
