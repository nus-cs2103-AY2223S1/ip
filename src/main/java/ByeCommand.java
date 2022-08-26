public class ByeCommand extends Command {
  @Override
  public void execute(TaskList taskList, Storage storage, Ui ui) {
    ui.showGoodbye();
  }

  public static boolean isBye(Command command) {
    return command instanceof ByeCommand;
  }
}
