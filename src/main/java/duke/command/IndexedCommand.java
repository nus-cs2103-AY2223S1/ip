package duke.command;

public abstract class IndexedCommand extends Command {
    protected int index;

    IndexedCommand(int index) {
        this.index = index;
    }
}
