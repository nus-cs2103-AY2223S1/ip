package duke.massops;

public class RangeOperation extends MassOperation {

    protected int startRange;
    protected int endRange;

    /**
     * Constructs the RangeOperation object with a start range and an end range
     *
     * @param startRange the index to start the execution from
     * @param endRange the index to end the task execution
     */
    public RangeOperation(int startRange, int endRange) {
        this.startRange = startRange;
        this.endRange = endRange;
    }

    public RangeOperation(int[] range) {
        this.startRange = range[0];
        this.endRange = range[1];
    }

    /**
     * Gets the operation identifier for the mass operation
     *
     * @return the operation identifier for the mass operation
     */
    @Override
    public String getOperationIdentifier() {
        return "range";
    }

    /**
     * Gets the number of tasks executed in the range operation
     *
     * @return the number of tasks being executed
     */
    public int getNumberOfTasksExecuted() {
        return endRange - startRange + 1;
    }

    public int getStartRange() { return startRange; }

    public int getEndRange() { return endRange; }
}
