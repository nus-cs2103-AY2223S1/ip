package cheese.command;

import cheese.exception.CheeseException;
import cheese.storage.Storage;
import cheese.ui.Ui;
import cheese.data.TaskList;

public abstract class Command {
  public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException;
}