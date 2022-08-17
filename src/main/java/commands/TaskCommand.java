import tasks.*;

public class TaskCommand is Command {
  
  public TaskCommand(String[] commandArgs, ArrayList<Task> tasks) {
    super(commandArgs, tasks);
  }

  @Override
  public boolean performAction() {
    if (this.commandArgs[0].equals("todo")) {
      this.tasks.add(new Todo(commandArgs[1]));

    } else if (this.commandArgs[0].equals("deadline")) {
      this.tasks.add(new Deadline(commandArgs[1], commandArgs[2]));

    } else if (this.commandArgs[0].equals("event")) {
      this.tasks.add(new Event(commandArgs[1], commandArgs[2]));
    } // TODO: else throw error
  }
  System.out.println("Hey sweetie, I've added: '" + userInput + "' to your lists of tasks~\n");
  return true;
}
