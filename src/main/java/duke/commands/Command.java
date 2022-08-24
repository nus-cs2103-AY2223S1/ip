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
   * Receives a TaskList and executes a command that may or may not modify the
   * TaskList
   * 
   * @param TaskList TaskList containing all tasks so far
   * @param storage Storage which handles data of TaskList
   * @param ui ui which prints messages to user
   * @throws DukeException in case of invalid input by user
   */

  boolean isExit = false;

  public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

  public boolean isExit() {
    return this.isExit;
  }

}
