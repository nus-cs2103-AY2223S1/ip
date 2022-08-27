package duke.command;

/**
 * Abstract superclass for Commands that access TaskList with an index.
 */
public abstract class IndexedCommand extends Command {
    protected int index;

    /**
     * Constructor for IndexedCommand.
     *
     * @param index Index of Task in TaskList.
     */
    IndexedCommand(int index) {
        this.index = index;
    }
}
