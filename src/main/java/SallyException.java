public class SallyException extends Exception {
    public SallyException(String message) {
        super(message);
    }

    public static class SallyTaskNotFoundException extends SallyException {
        @Override
        public String toString() {
            return Sally.border() + "\nI can't find this task number. You might want to check your list again!\n" + Sally.border();
        }
    }

    public static class SallyNoDescriptionException extends SallyException {
        @Override
        public String toString() {
            return Sally.border() + "\nOops! The description cannot be empty.\n" + Sally.border();
        }
    }

    public static class SallyInvalidInputException extends SallyException {
        @Override
        public String toString() {
            return Sally.border() + "\nOops! I'm sorry, I don't understand that :(\n" + Sally.border();
        }
    }

    public static class SallyNoDeadlineException extends SallyException {
        @Override
        public String toString() {
            return Sally.border() + "\nOops! Deadline has to be followed by '/by' and deadline time\n" + Sally.border();
        }
    }

    public static class SallyNoPlaceException extends SallyException {
        @Override
        public String toString() {
            return Sally.border() + "\nOops! Event has to be followed by '/at' and event place\n" + Sally.border();
        }
    }
}
