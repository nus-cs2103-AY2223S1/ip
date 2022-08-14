public class EventException extends SkylarkException{

    private String description;

    public EventException(String description) {
        super(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
