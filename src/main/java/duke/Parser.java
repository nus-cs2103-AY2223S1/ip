package duke;

import java.util.Objects;

/**
 * Parses user command.
 *
 * @author Lai Han Wen
 */
public class Parser {

    public Parser() {}

    /**
     * Parses user command into a String array.
     *
     * @param command User input
     * @return A String array. First element of the array is the type of command;
     *         i.e. "T" for todo, "D" for deadline, "E" for event, "d" for delete,
     *         "M" for mark, "L" for list, "B" for bye and "U" for unknown input.
     *         Second element of the array is either: the description of a todo/
     *         deadline/event task or the number of task to be marked/deleted.
     *         The third element of the array is the time for deadline/event tasks.
     */
    public String[] parseCommand(String command) {

        if (command.startsWith("todo")) {
            return this.getTodoArray(command);
        }

        if (command.startsWith("deadline")) {
            return this.getDeadlineArray(command);
        }

        if (command.startsWith("event")) {
            return this.getEventArray(command);
        }

        if (command.startsWith("delete")) {
            return this.getDeleteArray(command);
        }

        if (command.startsWith("mark")) {
            return this.getMarkArray(command);
        }

        if (Objects.equals(command, "list")) {
            return this.getListArray();
        }

        if (command.startsWith("find")) {
            return this.getFindArray(command);
        }

        if (Objects.equals(command, "bye")) {
            return this.getByeArray();
        }

        return this.getUnknownInputArray(); // reaches here if input is unknown
    }

    private String[] getTodoArray(String input) {
        if (input.replace(" ", "").length() == 4) {
            return new String[]{"T", ""}; // if todo is empty
        }
        String description = input.substring(5);
        return new String[]{"T", description};
    }

    private String[] getDeadlineArray(String input) {
        if (input.replace(" ", "").length() == 8) {
            return new String[]{"D", "", ""}; // if deadline is empty
        }
        String description = input.substring(9, input.indexOf('/') - 1);
        String by = input.substring(input.indexOf('/') + 4);
        return new String[]{"D", description, by};
    }

    private String[] getEventArray(String input) {
        if (input.replace(" ", "").length() == 5) {
            return new String[]{"E", "", ""}; // if event is empty
        }
        String description = input.substring(6, input.indexOf('/') - 1);
        String at = input.substring(input.indexOf('/') + 4);
        return new String[]{"E", description, at};
    }

    private String[] getDeleteArray(String input) {
        String numString = input.replace("delete", "")
                .replace(" ", "");
        return new String[]{"d", numString};
    }

    private String[] getMarkArray(String input) {
        String numString = input.replace("mark", "")
                .replace(" ", "");
        return new String[]{"M", numString};
    }

    private String[] getListArray() {
        return new String[]{"L"};
    }

    private String[] getByeArray() {
        return new String[]{"B"};
    }

    private String[] getUnknownInputArray() {
        return new String[]{"U"};
    }

    private String[] getFindArray(String input) {
        String keyword = input.substring(5);
        return new String[]{"F", keyword};
    }
}


