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
        } else if (message.equals("non integer input when marking")) {
            this.msg = "☹ OOPS!!! You did not give an integer for the task number :-(";
        } else if (message.equals("non integer input when deleting")) {
            this.msg = "☹ OOPS!!! You did not give an integer for the task number :-(";
        } else if (message.equals("index out of bounds")) {
            this.msg = "☹ OOPS!!! The index number is out of bounds :-(";
        } else {
            this.msg = "unknown error";
        }
    }
    @Override
    public String toString() {
        return msg;
    }
}
