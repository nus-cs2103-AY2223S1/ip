public class LoadCommand extends Command{
  @Override
  public void execute(Ui ui, StorageList storageList) throws DukeException {
    String filename = Duke.getDefaultFileName();
    String input = ui.getLastInput();
    if (input.split(" ").length == 2) {
      filename = input.split(" ")[1];
    }

    FileIO.load(storageList, filename);
    Output.LOAD.print();
  }
}
