package commands;

import components.DukeException;
import components.Storage;
import components.TaskList;

/**
 * Command interface that consist of execute method.
 */
public interface Command {
  /**
   * Does the functionality of the specific command.
   *
   * @param tasks Tasklist that stores the various tasks.
   * @param storage Storage to load and save files.
   * @param str Input from the user.
   * @return Output to the user after executing the command.
   */
  String execute(TaskList tasks, Storage storage, String line) throws DukeException;
}
