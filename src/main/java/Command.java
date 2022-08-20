public abstract class Command {
  protected boolean isExit = false;

  public boolean isExit() {
    return isExit;
  }
  
  public abstract void execute(Ui ui, StorageList storageList) throws DukeException;
}
