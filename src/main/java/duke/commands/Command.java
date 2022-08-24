package duke.commands;

import duke.exception.*;
import duke.main.Storage;
import duke.main.Ui;
import duke.tasks.*;

/**
 * Parent class of all commands with single execute method
 */

public abstract class Command {
  /**
   * Receives an tasklist and executes a command that may or may not modify the
   * tasklist
   * 
   * @param TaskList
   * @param storage
   * @param ui
   * @throws DukeException
   */

  boolean isExit = false;

  public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

  public boolean isExit() {
    return this.isExit;
  }

}
