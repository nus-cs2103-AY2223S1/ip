import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String s) throws DukeException {
        String[] userInput = s.trim().split(" ", 2);

        try {
            switch (userInput[0]) {
            case "bye":
                if (userInput.length > 1) {
                    throw new DukeException("Unrecognized command.");
                } else {
                    return new GoodbyeCommand();
                }

            case "list":
                if (userInput.length > 1) {
                    throw new DukeException("Unrecognized command.");
                } else {
                    return new ListCommand();
                }

            case "todo":
                if (userInput.length < 2) {
                    throw new DukeException("Missing todo description.");
                } else {
                    return new ToDoCommand(userInput[1].trim());
                }

            case "event":
                if (userInput.length < 2) {
                    throw new DukeException("Missing event description.");
                } else {
                    String[] tmp = userInput[1].trim().split("/at");
                    if (tmp.length == 2) {
                        return new EventCommand(
                                tmp[0].trim(),
                                LocalDateTime.parse(
                                        tmp[1].trim(),
                                        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
                    } else {
                        throw new DukeException("Missing /at tag or date for event.");
                    }
                }

            case "deadline":
                if (userInput.length < 2) {
                    throw new DukeException("Missing deadline description.");
                } else {
                    String[] tmp = userInput[1].trim().split("/by");
                    if (tmp.length == 2) {
                        return new DeadlineCommand(
                                tmp[0].trim(),
                                LocalDateTime.parse(
                                        tmp[1].trim(),
                                        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));

                    } else {
                        throw new DukeException("Missing /by tag or date for deadline.");
                    }
                }

            case "mark":
                if (userInput.length < 2) {
                    throw new DukeException("Missing task to mark.");
                } else {
                    int tmp = Integer.parseInt(userInput[1]);
                    return new MarkCommand(tmp);
                }

            case "unmark":
                if (userInput.length < 2) {
                    throw new DukeException("Missing task to unmark.");
                } else {
                    int tmp = Integer.parseInt(userInput[1]);
                    return new UnmarkCommand(tmp);
                }


            default:
                throw new DukeException("Unrecognized command.");
            }
        } catch (DateTimeParseException err) {
            throw new DukeException("Invalid date format. Type in \"yyyy-mm-dd HHmm\" format.");
        } catch (NumberFormatException err) {
            throw new DukeException("Wrong argument passed to mark/unmark. Type in a number.");
        } catch (DukeException err) {
            throw err;
        }
    }
}
