package commands;

import tasks.Task;
import java.util.ArrayList;

public class ByeCommand extends Command {

  public ByeCommand(String[] commandArgs, ArrayList<Task> tasks) {
    super(commandArgs, tasks);
  }

  @Override
  public boolean performAction() {
    System.out.println("MumBot: Goodbyeeee sweetheart <3");
    return false;
  }
}

