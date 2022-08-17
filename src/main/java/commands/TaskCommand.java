import tasks.*;

public class TaskCommand is Command {
  
  public TaskCommand(String[] commandArgs, ArrayList<Task> tasks) {
    super(commandArgs, tasks);
  }

  @Override
  public boolean performAction() {
    if (this.commandArgs[0].equals("todo")) {
      this.tasks.add(Todo(commandArgs[1]));

    } else if (this.commandArgs[0].equals("deadline")) {
      this.tasks.add(Deadline(commandArgs[1], commandArgs[2]));

    } else if (this.commandArgs[0].equals("event")) {
      this.tasks.add(Event(commandArgs[1], commandArgs[2]));
    } // TODO: else throw error
  }
}
