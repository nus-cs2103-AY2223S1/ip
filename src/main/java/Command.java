public abstract class Command {
  public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws CheeseException;
}