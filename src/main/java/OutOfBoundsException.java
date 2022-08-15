class OutOfBoundsException extends CarbonException {
    public OutOfBoundsException(int taskNumber, int length) {
        super(String.format(
                "%d is a little awkward. We have %d tasks.", 
                taskNumber, 
                length)
                );
    }

    @Override
    public String toString() {
        return String.format(
                "%s\n    %s\n    Maybe you would like to try that again.",
                super.toString(),
                this.getMessage()
                );
    }
}
