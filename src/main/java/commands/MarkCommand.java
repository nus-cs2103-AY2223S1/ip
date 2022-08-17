import tasks.Task;

public class MarkCommand is Command {

  public MarkCommand(String[] commandArgs, ArrayList<Task> tasks) {
    super(commandArgs, tasks);
  }

  @Override
  public boolean performAction() {
    if (this.commandArgs[0].equals("mark")) {
      Integer index = Integer.parseInt(this.commandArgs[1]) - 1;
      Task taskToUpdate = this.tasks.get(index);
      System.out.println(taskToUpdate.updateStatus(true) + "\n");
      return true;

    } else if (this.commandArgs[0].equals("unmark")) {
      Integer index = Integer.parseInt(this.commandArgs[1]) - 1;
      Task taskToUpdate = this.tasks.get(index);
      System.out.println(taskToUpdate.updateStatus(false) + "\n");
      return true;
    } //TODO: else throw error
  }
}
