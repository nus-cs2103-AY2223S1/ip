package kohaku.daveexceptions;

public class DaveException extends Exception {

    private final String error;

    public DaveException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return this.error;
    }

    @Override
    public String getMessage() {
        return this.error;
    }
}
