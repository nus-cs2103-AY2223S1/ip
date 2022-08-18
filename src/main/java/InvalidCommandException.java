public class InvalidCommandException extends DukeException {
    private String cmd;

    public InvalidCommandException(String s) {
        this.cmd = s;
    }

    @Override
    public String toString() {
        return '"' + this.cmd + '"';
    }
}
