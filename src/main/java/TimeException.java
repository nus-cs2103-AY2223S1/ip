public class TimeException extends DukeException{
    public TimeException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "please input a time (/at for events and /by for deadlines)";
    }
}
