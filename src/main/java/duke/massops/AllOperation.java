package duke.massops;

/**
 * Encapsulates a MassOperation that executes a command to all instances of the task
 */
public class AllOperation extends MassOperation {

    /**
     * Gets the operation identifier for the mass operation
     *
     * @return the operation identifier for the mass operation
     */
    @Override
    public String getOperationIdentifier() {
        return "all";
    }
}
