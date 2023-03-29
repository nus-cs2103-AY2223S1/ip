package duke.massops;

/**
 * Encapsulates a mass operation that operates only on a single input
 */
public class SingleOperation extends MassOperation {

    private int index;

    /**
     * Constructs the SingleOperation object
     *
     * @param index the index of the task to be executed
     */
    public SingleOperation(int index) {
        this.index = index;
    }

    /**
     * Gets the operation identifier for the mass operation
     *
     * @return the operation identifier for the mass operation
     */
    @Override
    public String getOperationIdentifier() {
        return "single";
    }

    /**
     * Returns the index of the specified task to be operated on
     *
     * @return the index of the specified task to be operated on
     */
    public int getIndex() {
        return index;
    }
}
