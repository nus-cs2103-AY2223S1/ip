public class RoofusException {

    public static class IndexNotInListException extends Exception {
        IndexNotInListException(String errMessage) {
            super(errMessage);
        }
    }

    public static class WrongFormatException extends Exception {
        WrongFormatException(String errMessage, Throwable err) {
            super(errMessage, err);
        }
    }

    public static class EmptyDescriptionException extends Exception {
        EmptyDescriptionException(String errMessage, Throwable err) {
            super(errMessage, err);
        }
    }
}
