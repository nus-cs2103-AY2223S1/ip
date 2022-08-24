class InvalidTimeException extends CarbonException {
    public InvalidTimeException(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return String.format(
                "%s\n    The time '%s' looks a little wonky.\n"
                + "    Please input in YYYY-MM-DD format.",
                super.toString(),
                this.getMessage()
                );
    }
}
