public class ListItemsCommand implements Command {
  @Override
  public void execute(TaskList itemList, Ui ui, Storage storage) throws DukeException {
    ui.showToUser(itemList.toString());
  }
}
