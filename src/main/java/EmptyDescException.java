public class EmptyDescException extends DukeException {
    private static String line = "_______________________________________";

    public EmptyDescException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        String ret = line + "\n" + "OHNOOOOOO!!! The description of a " + this.getMessage()
                + " cannot be empty.\n" + line;
        return ret;
    }
}
