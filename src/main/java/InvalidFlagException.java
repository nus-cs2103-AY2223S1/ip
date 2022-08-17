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
                "%s\n    Looks like you're missing the '%s' flag for your %s.",
                super.toString(),
                this.flag,
                this.type
                );
    }
}
