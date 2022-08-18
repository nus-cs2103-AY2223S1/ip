public class DaveException extends Exception {

    private String error;

    public DaveException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return this.error;
    }
}
