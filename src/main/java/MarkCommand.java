public class MarkCommand implements Command {
  private final String index;

  public MarkCommand(String index) {
    this.index = index;
  }

  @Override
  public void execute(TaskList itemList, Ui ui, Storage storage) throws DukeException {
    ui.showToUser(itemList.mark(Integer.parseInt(index)));
  }
}
