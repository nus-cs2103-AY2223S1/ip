public class UnmarkCommand extends Command{
  @Override
  public void execute(Ui ui, StorageList storageList) throws DukeException {
    int index = Parser.getIndex(ui.getLastInput());
    storageList.unmark(index);
    Output.UNMARK.changeStatus(storageList.get(index));
  }
}
