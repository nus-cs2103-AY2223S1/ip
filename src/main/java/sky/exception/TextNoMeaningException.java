package sky.exception;

public class TextNoMeaningException extends Exception {
    String message;
    public TextNoMeaningException(String str) {
        message = str;
    }

    @Override
    public String toString() {
        return message;
    }
}
