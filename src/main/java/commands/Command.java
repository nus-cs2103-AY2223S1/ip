package commands;

import exception.DukeException;
import java.util.ArrayList;
import tasks.*;

/**
 * Parent class of all commands with single execute method
 */

@FunctionalInterface
public interface Command {
  /**
   * Receives an tasklist and executes a command that may or may not modify the tasklist
   * @param TaskList
   * @throws DukeException
   */

  void execute(ArrayList<Task> TaskList) throws DukeException;
}
