package cheese.command;

import cheese.storage.Storage;
import cheese.ui.Ui;
import cheese.data.TaskList;

public class UnknownCommand extends Command {
  @Override
  public void execute(TaskList taskList, Storage storage, Ui ui) {
    ui.showError("Sowwy, I don't understand");
  }
}
