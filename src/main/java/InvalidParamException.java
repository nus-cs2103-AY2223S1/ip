class InvalidParamException extends CarbonException {
    public InvalidParamException(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return String.format(
                "%s\n    Looks like you're missing the time for your '%s'.",
                super.toString(),
                this.getMessage()
                );
    }
}
