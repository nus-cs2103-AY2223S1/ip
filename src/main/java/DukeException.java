public class DukeException extends Exception {

    private String detail;
    private int errorType;

    public DukeException(int i) {
        this.errorType = i;
    }

    @Override
    public String toString() {
        if (this.errorType == 0) {
            return "    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                    "    ____________________________________________________________";
        }
        else if (this.errorType == 1) {
            return "    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                    "    ____________________________________________________________";
        }
        else if (this.errorType == 2) {
            return "    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of an event cannot be empty.\n" +
                    "    ____________________________________________________________";
        }
        return "    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "    ____________________________________________________________";
    }
}
