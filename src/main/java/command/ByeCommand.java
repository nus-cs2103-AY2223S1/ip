package command;

import java.util.ArrayList;

import task.Task;
import ui.Ui;

public class ByeCommand extends Command {

  public ByeCommand(String[] commandArgs, ArrayList<Task> tasks) {
    super(commandArgs, tasks);
  }

  @Override
  public boolean performAction() {
    Ui.print("MumBot: Goodbyeeee sweetheart <3");
    return false;
  }
}

