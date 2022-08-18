public class DukeException extends Exception {

    protected String msg;
    public DukeException(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
