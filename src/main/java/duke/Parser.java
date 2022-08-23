package duke;

public class Parser {
    public static CommandType parse(String userText) {
        String commandWord = userText.split(" ", 2)[0];

        if (commandWord.equals("bye")) {
            return CommandType.BYE;
        } else if (commandWord.equals("list")) {
            return CommandType.LIST;
        } else if (commandWord.equals("mark")) {
            return CommandType.MARK;
        } else if (commandWord.equals("unmark")) {
            return CommandType.UNMARK;
        } else if (commandWord.equals("todo")) {
            return CommandType.TODO;
        } else if (commandWord.equals("deadline")) {
            return CommandType.DEADLINE;
        } else if (commandWord.equals("event")) {
            return CommandType.EVENT;
        } else if (commandWord.equals("delete")) {
            return CommandType.DELETE;
        } else {
            return CommandType.UNABLE;
        }
    }
}
