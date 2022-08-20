package duke.command;

import duke.Output;
import duke.StorageList;
import duke.Ui;

public class ListCommand extends Command{
  @Override
  public void execute(Ui ui, StorageList storageList) {
    Output.LIST.list(storageList);
  }
}
