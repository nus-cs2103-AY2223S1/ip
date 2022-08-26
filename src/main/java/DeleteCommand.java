public class DeleteCommand implements Command {
  private final String index;

  public DeleteCommand(String index) {
    this.index = index;
  }

  @Override
  public void execute(TaskList itemList, Ui ui, Storage storage) throws DukeException {
    ui.showToUser(itemList.deleteItem(Integer.parseInt(index)));
  }
}
