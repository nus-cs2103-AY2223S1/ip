public abstract class DukeException extends Exception {


    DukeException(String message) {
        super(message);
    }

    private static class InvalidCommandException extends DukeException {
        private static final String invalidInputMessage = Duke.topWindow +
                "You're speaking a language i don't know. Try again?"
                + Duke.bottomWindow;

        InvalidCommandException() {
            super(invalidInputMessage);
        }
    }

    private static class EmptyCommandException extends DukeException {
        private static final String emptyCommandMessage = Duke.topWindow + "You didn't say what I should do!"
                + Duke.bottomWindow;

        EmptyCommandException() {
            super(emptyCommandMessage);
        }

    }

    private static class BlankCommandException extends DukeException {
        private static final String emptyCommandMessage = Duke.topWindow + "What should I do with the ";

        BlankCommandException(String name) {
            super(emptyCommandMessage + name + "?" + Duke.bottomWindow);
        }
    }

    public static DukeException ExceptionCreator(String exceptionType, String message) {
        switch (exceptionType) {
            case "InvalidCommand":
                return new InvalidCommandException();
            case "BlankCommand":
                return new BlankCommandException(message);
            default:
                return new EmptyCommandException();
        }
    }
}

