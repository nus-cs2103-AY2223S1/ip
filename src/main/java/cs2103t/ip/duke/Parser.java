package cs2103t.ip.duke;

public class Parser {

    public boolean isTodo(String input) {
        return input.startsWith("todo");
    }

    public boolean isEvent(String input) {
        return input.startsWith("event");
    }

    public boolean isDeadline(String input) {
        return input.startsWith("deadline");
    }

    public boolean isMark(String input) {
        return input.startsWith("mark");
    }

    public boolean isUnmark(String input) {
        return input.startsWith("unmark");
    }

    public boolean isDelete(String input) {
        return input.startsWith("delete");
    }

    public boolean isBye(String input) {
        return input.startsWith("bye");
    }

    public boolean isList(String input) {
        return input.startsWith("list");
    }

    public boolean isFind(String input) {
        return input.startsWith("find");
    }
}
