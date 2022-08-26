public interface Command {
  public abstract void execute(TaskList itemList, Ui ui, Storage storage)
      throws DukeException;
}
