package duke;

import java.util.Objects;

public class Parser {

    /**
     * Parses user command into a String array. First element of the array
     * is the type of command; i.e. "T" for todo, "D" for deadline, "E" for
     * event, "d" for delete, "M" for mark, "L" for list, "B" for bye and "U"
     * for unknown input. Second element of the array is either: the description
     * of a todo/deadline/event task or the number of task to be marked/deleted.
     * The third element of the array is the time for deadline/event tasks.
     *
     * @param command User input
     * @return a String array
     */
    public String[] parseCommand(String command) {

        String[] arr = this.isUnknown(); // to be returned

        if (command.contains("todo")) {
            try {
                arr = this.isTodo(command);
            } catch (DukeException e) {
                System.out.println(e);
            }
            return arr;
        }

        if (command.contains("deadline")) {
            try {
                arr = this.isDeadline(command);
            } catch (DukeException e) {
                System.out.println(e);
            }
            return arr;
        }

        if (command.contains("event")) {
            try {
                arr = this.isEvent(command);
            } catch (DukeException e) {
                System.out.println(e);
            }
            return arr;
        }

        if (command.contains("delete")) {
            arr = this.isDelete(command);
            return arr;
        }

        if (command.contains("mark")) {
            arr = this.isMark(command);
            return arr;
        }

        if (Objects.equals(command, "list")) {
            arr = this.isList();
            return arr;
        }

        if (Objects.equals(command, "bye")) {
            arr = this.isBye();
            return arr;
        }

        return arr; // reaches here if input is unknown
    }

    private String[] isTodo(String input) throws DukeException {
        if (input.replace(" ", "").length() == 4) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String description = input.substring(5);
        return new String[]{"T", description};
    }

    private String[] isDeadline(String input) throws DukeException {
        if (input.replace(" ", "").length() == 8) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String description = input.substring(9, input.indexOf('/') - 1);
        String by = input.substring(input.indexOf('/') + 4);
        return new String[]{"D", description, by};
    }

    private String[] isEvent(String input) throws DukeException {
        if (input.replace(" ", "").length() == 5) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String description = input.substring(6, input.indexOf('/') - 1);
        String at = input.substring(input.indexOf('/') + 4);
        return new String[]{"E", description, at};
    }

    private String[] isDelete(String input) {
        String numString = input.replace("delete", "")
                .replace(" ", "");
        return new String[]{"d", numString};
    }

    private String[] isMark(String input) {
        String numString = input.replace("mark", "")
                .replace(" ", "");
        return new String[]{"M", numString};
    }

    private String[] isList() {
        return new String[]{"L"};
    }

    private String[] isBye() {
        return new String[]{"B"};
    }

    private String[] isUnknown() {
        return new String[]{"U"};
    }
}


