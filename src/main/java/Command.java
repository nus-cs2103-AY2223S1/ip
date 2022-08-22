/**
 * The Command class deals with executing tasks involving TaskList, Ui, and Storage.
 */
public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws TextNoMeaningException;
    public abstract boolean isExit();
}
