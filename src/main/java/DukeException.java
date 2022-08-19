public class DukeException extends Exception {
    protected String msg;

    public DukeException(String s) {
        this.msg = s;
    }
}
