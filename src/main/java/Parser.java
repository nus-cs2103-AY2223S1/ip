import java.util.Objects;

public class Parser {

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

    public String[] isTodo(String input) throws DukeException {
        if (input.replace(" ", "").length() == 4) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String description = input.substring(5);
        return new String[]{"T", description};
    }

    public String[] isDeadline(String input) throws DukeException {
        if (input.replace(" ", "").length() == 8) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String description = input.substring(9, input.indexOf('/') - 1);
        String by = input.substring(input.indexOf('/') + 4);
        return new String[]{"D", description, by};
    }

    public String[] isEvent(String input) throws DukeException {
        if (input.replace(" ", "").length() == 5) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String description = input.substring(6, input.indexOf('/') - 1);
        String at = input.substring(input.indexOf('/') + 4);
        return new String[]{"E", description, at};
    }

    public String[] isDelete(String input) {
        String numString = input.replace("delete", "")
                .replace(" ", "");
        return new String[]{"d", numString};
    }

    public String[] isMark(String input) {
        String numString = input.replace("mark", "")
                .replace(" ", "");
        return new String[]{"M", numString};
    }

    public String[] isList() {
        return new String[]{"L"};
    }

    public String[] isBye() {
        return new String[]{"B"};
    }

    public String[] isUnknown() {
        return new String[]{"U"};
    }
}


