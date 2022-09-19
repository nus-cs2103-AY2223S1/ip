package duke;

/**
 * Represents the abstract concept of a Command, which could be run in Duke.
 */
public abstract class Command {
    /**
     * Abstract method to run the Command.
     *
     * @param duke Duke instance to run the Command at.
     * @throws DukeException If run is unsuccessful.
     */
    public abstract void run(Duke duke) throws DukeException;

    /**
     * Checks equality to another Object.
     *
     * @param obj Other Object.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof Command && getClass() == obj.getClass());
    }
}
