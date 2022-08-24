import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    protected Command parse(String input) throws DukeException {
        String[] strings = input.split(" ", 2);
        if (strings.length == 1) { //input is guaranteed to not be empty
            return singleCommand(strings);
        } else {
            return doubleCommand(strings);
        }
    }

    private Command singleCommand(String[] arg) throws DukeException {
        switch (arg[0]) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            default:
                throw new InputException();
        }
    }

    private Command doubleCommand(String[] arg) throws DukeException {
        switch (arg[0]) {
            case "mark":
            case "unmark":
            case "delete":
                if (arg.length == 1) {
                    throw new MarkException();
                }
                try {
                    int num = Integer.parseInt(arg[1]);
                    if (arg[0].equals("mark")) {
                        return new MarkCommand(num);
                    } else if (arg[0].equals("unmark")) {
                        return new UnmarkCommand(num);
                    } else {
                        return new DeleteCommand(num);
                    }
                } catch (NumberFormatException e) {
                    throw new MarkException();
                }

            case "deadline":
            case "event":
                if (arg.length == 1 || arg[1].isEmpty()) {
                    throw new TaskException();
                } else {
                    String[] split = arg[1].split("/", 2);
                    if (split.length < 2) {
                        throw new TimeException();
                    } else {
                        try {
                            if (split[1].substring(3).length() < 11) {
                                split[1] = split[1] + " 23:59";
                            }
                            LocalDateTime time = LocalDateTime.parse(split[1].substring(3), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                            if (arg[0].equals("deadline")) {
                                return new DeadlineCommand(split[0], time);
                            } else {
                                return new EventCommand(split[0], time);
                            }
                        } catch (DateTimeParseException e) {
                            throw new DateTimeException();
                        }
                    }
                }

            case "todo":
                if (arg.length == 1) {
                    throw new TaskException();
                } else {
                    return new TodoCommand(arg[1]);
                }

            default:
                throw new InputException();
        }
    }
}
