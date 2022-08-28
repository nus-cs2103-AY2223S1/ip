public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        String lineBlock = "     -----------------------------------------";
        return String.format("     %s\n%s\n%s", lineBlock, super.toString(), lineBlock);
    }
}