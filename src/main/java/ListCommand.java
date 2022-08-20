public class ListCommand extends Command{
  @Override
  public void execute(Ui ui, StorageList storageList) {
    Output.LIST.list(storageList);
  }
}
