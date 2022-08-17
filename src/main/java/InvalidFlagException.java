class InvalidFlagException extends CarbonException {
    private String type;
    private String flag;

    public InvalidFlagException(String input, String type) {
        super(input);
        this.type = type;
        if (type == "deadline") {
            this.flag = "/by";
        } else {
            this.flag = "/at";
        }
    }

    @Override
    public String toString() {
        return String.format(
                "%s\n    Use '%s' to indicate the time for your %s.",
                super.toString(),
                this.flag,
                this.type
                );
    }
}
