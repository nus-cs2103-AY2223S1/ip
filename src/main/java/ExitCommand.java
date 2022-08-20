public class ExitCommand extends Command{
  @Override
  public void execute(Ui ui, StorageList storageList) {
    Output.GOODBYE.print();
    isExit = true;
  }
}
