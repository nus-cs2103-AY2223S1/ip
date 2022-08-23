package commands;

import exception.*;
import main.Storage;
import main.Ui;
import tasks.*;

/**
 * ByeCommand says bye to user
 */
public class ByeCommand extends Command {

  public ByeCommand() {
    this.isExit = true;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    System.out.println("Bye. Hope to see you again soon!");

  }
}
