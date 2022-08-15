class InvalidInputException extends CarbonException {
    public InvalidInputException(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return String.format(
                "%s\n    '%s'? I have no idea what that is.",
                super.toString(),
                this.getMessage()
                );
    }
}
