abstract class DukeException extends Exception{
    private static final String DIVIDER = "-------------------------------------\n";
    public DukeException(String msg) {
        super(DIVIDER + msg + DIVIDER);
    }
}
