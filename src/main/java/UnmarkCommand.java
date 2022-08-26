public class UnmarkCommand implements Command {
  private final String index;

  public UnmarkCommand(String index) {
    this.index = index;
  }

  @Override
  public void execute(TaskList itemList, Ui ui, Storage storage) throws DukeException {
    ui.showToUser(itemList.unmark(Integer.parseInt(index)));
  }
}
