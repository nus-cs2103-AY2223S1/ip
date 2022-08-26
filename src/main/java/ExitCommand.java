public class ExitCommand implements Command {
  @Override
  public void execute(TaskList itemList, Ui ui, Storage storage) {
    itemList.save(storage);
    ui.showOutro();
    System.exit(0);
  }
}
