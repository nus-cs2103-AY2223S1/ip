public class ToDoException extends SkylarkException{
    public ToDoException(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }
}
