package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    enum Keyword {
        bye, list, mark, unmark, todo, deadline, event, delete
    }

    enum TaskKeyword {
        todo, deadline, event
    }

    public static Command parse(String commandText) throws DukeException {
        String keyword = getCommandKey(commandText);
        String content = getCommandContent(commandText);
        isValidKeyword(keyword);
        return new Command(keyword, content);
    }

    private static String getCommandKey(String command) {
        int index = command.indexOf(' ');
        if (index == -1) {
            return command;
        }
        return command.substring(0, index);
    }

    private static String getCommandContent(String command) {
        int index = command.indexOf(' ');
        if (index == -1) {
            return "";
        }
        return command.substring(index).trim();
    }

    protected static String formatDate(String desc) {
        int index = desc.indexOf('/');
        /*if (index > 0) {
            return LocalDate.parse(desc.substring(index + 1), DateTimeFormatter.ofPattern("yyyy-mm-dd")).
                    format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }*/
        return desc;
    }

    private static boolean isKeyword(String keyword) {
        for (Keyword k : Keyword.values()) {
            if (k.name().equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isTaskKeyword(String keyword) {
        for (TaskKeyword tk : TaskKeyword.values()) {
            if (tk.name().equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    protected static void isValidTaskCommand (String keyword, String content) throws DukeException {
        if (content.isBlank()) {
            throw new DukeException(String.format("The description of a %s cannot be empty", keyword));
        }
    }

    private static void isValidKeyword(String keyword) throws DukeException {
        if (!isKeyword(keyword) || keyword.isBlank()) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
