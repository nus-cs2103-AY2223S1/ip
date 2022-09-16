package duke;

/**
 * Represents the abstract concept of a Command, which could be run in Duke.
 */
public abstract class Command {
    /**
     * Abstract method to run the Command.
     *
     * @param duke Duke instance to run the Command at.
     */
    public abstract void run(Duke duke);
}
