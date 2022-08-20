public class DukeException extends Exception {
    private static String line = "_______________________________________";

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        String ret = line + "\nohai, please provide proper input :<<<<<\n" + line;
        return ret;
    }
}