public class ToDoException extends SkylarkException{

    private String description;

    public ToDoException(String description) {
        super(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
