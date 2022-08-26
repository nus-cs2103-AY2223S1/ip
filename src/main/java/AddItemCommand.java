public class AddItemCommand implements Command {
  private final String[] input;
  private final Parser parser;

  public AddItemCommand(String[] input, Parser parser) {
    this.input = input;
    this.parser = parser;
  }

  @Override
  public void execute(TaskList itemList, Ui ui, Storage storage) throws DukeException {
    ui.showToUser(parser.parseAddItem(input));
  }
}
