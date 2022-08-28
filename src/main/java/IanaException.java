public class IanaException extends Exception {
    public IanaException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        String lineBlock = "     -----------------------------------------";
        return String.format("     %s\n%s\n%s", lineBlock, super.toString(), lineBlock);
    }
}