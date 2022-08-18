public class SallyException extends Exception {
    public SallyException() {
        super();
    }

    public static class SallyTaskNotFoundException extends SallyException {
        @Override
        public String toString() {
            return "I can't find this task number. You might want to check your list again!";
        }
    }

    public static class SallyNoDescriptionException extends SallyException {
        @Override
        public String toString() {
            return "Oops! The description cannot be empty.";
        }
    }

    public static class SallyInvalidInputException extends SallyException {
        @Override
        public String toString() {
            return "Oops! I'm sorry, I don't understand that :(";
        }
    }

    public static class SallyNoDeadlineException extends SallyException {
        @Override
        public String toString() {
            return "Oops! Deadline has to be followed by '/by' and deadline time";
        }
    }

    public static class SallyNoPlaceException extends SallyException {
        @Override
        public String toString() {
            return "Oops! Event has to be followed by '/at' and event place";
        }
    }
}
