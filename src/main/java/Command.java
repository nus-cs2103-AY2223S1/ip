import java.util.ArrayList;

/**
 * Represents a Command
 */
public abstract class Command {
    /**
     * Executes the Command
     * @throws InvalidFormatException
     */
    public abstract void deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException;
}
