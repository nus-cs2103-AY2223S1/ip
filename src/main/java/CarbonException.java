abstract class CarbonException extends RuntimeException {
    class InvalidInputException extends CarbonException {
        private String input;

        public InvalidInputException(String input) {
            super(input);
            this.input = input;
        }

        @Override
        public String toString() {
            return String.format(
                    "%s\n    '%s'? I have no idea what that is.",
                    super.toString(),
                    this.input
                    );
        }
    }

    public CarbonException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Hold up, something's off.";
    }
}
