public class descriptionException extends Exception {
    public descriptionException() {
        super("Empty Description not allowed");
    }

    @Override
    public String toString() {
        return "OOOOPSSSS!! The description of a task cannot be empty!!";
    }
}
