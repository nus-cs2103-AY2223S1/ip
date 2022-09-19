package duke.massops;

/**
 * Encapsulates a RangeOperation in Duke
 */
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

    /**
     * Constructs a RangeOperation object with an integer array of a start range as the first element and
     * an end range as the second element
     * @param range
     */
    public RangeOperation(int[] range) {
        assert(range.length == 2);
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

    public int getStartRange() {
        return startRange;
    }

    public int getEndRange() {
        return endRange;
    }
}
