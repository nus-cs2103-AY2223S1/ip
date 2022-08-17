public class DekuExceptions extends Exception{
    private final String message;

    public DekuExceptions(String errorMsg) {
        super("AUUUUUGH! " + errorMsg);
        message = errorMsg;
    }

    @Override
    public String toString() {
        return "AUUUUUGH! " + message;
    }
}
