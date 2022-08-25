package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;


public class Parser {

    public static int parseTaskNumber(String userInput) throws DukeException {
        String[] parsedInput = userInput.split(" ");
        String taskNumberString = parsedInput[1];
        try {
            return Integer.parseInt(taskNumberString) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("   " + taskNumberString + " is not a valid task number.\n");
        }

    }

    public static Todo parseTodoTask(String userInput) {
        String[] parsedUserResponse = userInput.split(" ");
        String newTaskDescription = String.join(" ", Arrays.copyOfRange(parsedUserResponse,1, parsedUserResponse.length));
        return new Todo(newTaskDescription);
    }

    public static Event parseEventTask(String userInput) {

        String[] parsedUserResponse = userInput.split(" ");
        int atSeparationIndex = Arrays.asList(parsedUserResponse).indexOf("/at");
        String newTaskDescription = String.join(" ",
                Arrays.copyOfRange(parsedUserResponse,
                        1,
                        atSeparationIndex));
        String newEventTime = String.join(" ",
                Arrays.copyOfRange(parsedUserResponse,
                        atSeparationIndex + 1,
                        parsedUserResponse.length));
        return new Event(newTaskDescription, newEventTime);
    }

    public static Deadline parseDeadlineTask(String userInput) throws DukeException {
        String[] parsedUserResponse = userInput.split(" ");
        int bySeparationIndex = Arrays.asList(parsedUserResponse).indexOf("/by");
        String newTaskDescription = String.join(" ",
                Arrays.copyOfRange(parsedUserResponse,
                        1,
                        bySeparationIndex));
        try {
            LocalDate deadline = LocalDate.parse(parsedUserResponse[bySeparationIndex + 1]);
            return new Deadline(newTaskDescription, deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("    " + "Error: No date added for the deadline.\n");
        } catch (DateTimeParseException e) {
            throw new DukeException("    " + "Error: Invalid deadline format, please key in as yyyy-mm-dd\n");
        }
    }

    public static Command parseUserResponse(String userResponse) throws DukeException {
        String[] parsedUserResponse = userResponse.split(" ");
        String mainCommand = parsedUserResponse[0];
        switch(mainCommand) {
            case "bye":
                if (parsedUserResponse.length > 1) {
                    throw new DukeException("    " + "Invalid number of arguments, only one required\n");
                } else {
                    return Command.BYE;
                }
            case "list":
                if (parsedUserResponse.length > 1) {
                    throw new DukeException("    " + "Invalid number of arguments, only one required\n");
                } else {
                    return Command.LIST;
                }
            case "mark":
                if (parsedUserResponse.length != 2) {
                    throw new DukeException("    " + "Invalid number of arguments, two required\n");
                } else {
                    return Command.MARK;
                }
            case "unmark":
                if (parsedUserResponse.length != 2) {
                    throw new DukeException("    " + "Invalid number of arguments, two required\n");
                } else {
                    return Command.UNMARK;
                }
            case "todo":
                if (parsedUserResponse.length < 2) {
                    throw new DukeException("    " + "☹ OOPS!!! The description of a todo cannot be empty.\n");
                } else {
                    return Command.TODO;
                }
            case "deadline":
                int bySeparationIndex = Arrays.asList(parsedUserResponse).indexOf("/by");
                if (bySeparationIndex == -1) {
                    throw new DukeException("    " + "Error: No date added for the deadline.\n");
                } else {
                    return Command.DEADLINE;
                }
            case "event":
                int atSeparationIndex = Arrays.asList(parsedUserResponse).indexOf("/at");
                if (atSeparationIndex == -1) {
                    throw new DukeException("    " + "Error: No date added for the event.\n");
                } else {
                    return Command.EVENT;
                }
            case "delete":
                if (parsedUserResponse.length != 2) {
                    throw new DukeException("    " + "Invalid number of arguments, two required\n");
                } else {
                    return Command.DELETE;
                }
            default:
                throw new DukeException("    " + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }
}
