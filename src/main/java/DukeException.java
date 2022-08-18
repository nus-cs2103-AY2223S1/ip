public class DukeException extends Exception{
    public DukeException(String str) {
        super(str);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!!";
    }
}
