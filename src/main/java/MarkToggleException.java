public class MarkToggleException extends Exception{
    @Override
    public String toString() {
        return "MarkToggleException. Tried to mark/unmark a complete/incomplete task.";
    }
}
