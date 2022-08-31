package sky.exception;

public class TextNoMeaningException extends Exception {
    private String message;

    public TextNoMeaningException(String str) {
        this.message = str;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
