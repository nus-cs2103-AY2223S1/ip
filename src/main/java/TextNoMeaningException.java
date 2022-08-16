public class TextNoMeaningException extends Exception {
    String message;
    TextNoMeaningException(String str) {
        message = str;
    }

    @Override
    public String toString() {
        return message;
    }
}
