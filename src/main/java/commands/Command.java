package commands;

import tasks.Task;
import java.util.ArrayList;
import exceptions.DukeException;

public abstract class Command {

  protected String[] commandArgs;
  protected ArrayList<Task> tasks;

  public Command(String[] commandArgs, ArrayList<Task> tasks) {
    this.commandArgs = commandArgs;
    this.tasks = tasks;
  }

  /*
   * @return Returns true if the programme should continue seeking user input.
   * Returns false if the programme is to be terminated.
   */
  public abstract boolean performAction();
}

