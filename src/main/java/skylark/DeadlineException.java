package skylark;

public class DeadlineException extends SkylarkException {

    private final String description;

    public DeadlineException(String description) {
        super(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
