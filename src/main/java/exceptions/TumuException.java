public abstract class TumuException extends Exception {
    private String message;

    protected TumuException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
