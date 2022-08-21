package duke.command;

import duke.*;

import java.time.LocalDateTime;

/**
 * Matches the deadlines with the provided date in the StorageList.
 */
public class MatchCommand extends Command{
  /**
   * Executes the match command.
   * @param ui the user interface
   * @param storageList the storage list
   * @throws DukeException if the command is invalid
   */
  @Override
  public void execute(Ui ui, StorageList storageList) throws DukeException {
    String input = ui.getLastInput();
    String command = ui.getLastCommand();
    Output.DATE.listMatches(storageList, LocalDateTime.parse(Parser.findFirstCommand(input, command), Ui.getInputFormatter()));
  }
}
