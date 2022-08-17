public class DukeException{
    private String message;

    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString()  {
        return "☹ OOPS!!! " + message;
    }
}
