package exceptions;

public class TaskDescriptionEmpty extends HazellException {
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }
}
