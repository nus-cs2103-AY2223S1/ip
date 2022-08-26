public class UnknownCommand extends Command {
  @Override
  public void execute(TaskList taskList, Storage storage, Ui ui) {
    ui.showError("Sowwy, I don't understand");
  }
}
