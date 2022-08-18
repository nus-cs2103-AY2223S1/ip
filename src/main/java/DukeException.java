public class DukeException {
    String msg;
    DukeException(String message) {
        if (message.equals("todo")) {
            this.msg = "☹ OOPS!!! The description of a todo cannot be empty.";
        } else if (message.equals("deadline")) {
            this.msg = "☹ OOPS!!! The description of a deadline cannot be empty.";
        } else if (message.equals("event")) {
            this.msg = "☹ OOPS!!! The description of an event cannot be empty.";
        } else if (message.equals("deadline format")) {
            this.msg = "☹ OOPS!!! The formatting of the deadline message is wrong.";
        } else if (message.equals("event format")) {
            this.msg = "☹ OOPS!!! The formatting of the event message is wrong.";
        } else if (message.equals("unknown")) {
            this.msg = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        } else {
            this.msg = "unknown error";
        }
    }
    @Override
    public String toString() {
        return msg;
    }
}
