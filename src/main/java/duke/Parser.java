package duke;

import duke.command.*;

import java.util.Arrays;

/**
 * Parser parses and helps to make sense of user input.
 */
public class Parser {
    private enum Commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    private final static String INDENTATION = "   ";

    /**
     * Returns a command to carry out based on the user input.
     * @param fullCommand user input.
     * @return Command to carry out.
     * @throws DukeException
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] userInput = fullCommand.trim().split(" ");
        Commands command = Commands.valueOf(userInput[0].toUpperCase());
        if (userInput.length == 1) {
            switch (command) {
                case BYE:
                    return new ExitCommand();
                case LIST:
                    return new ListCommand();
                case MARK:
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! The mark command should be used as shown. " +
                            "eg. mark {num of task in list to be marked as done}");
                case UNMARK:
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! The unmark command should be used as shown. " +
                            "eg. mark {num of task in list to be unmarked as incomplete}");
                case TODO:
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! The description of a todo cannot be empty, " +
                            "usage of todo is as shown. eg. todo {task to be done}");
                case DEADLINE:
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! The description of a deadline cannot be empty, " +
                            "usage of deadline is as shown. " +
                            "eg. deadline {task to be done} /by {date/time to complete}");
                case EVENT:
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! The description of a event cannot be empty, " +
                            "usage of event is as shown. " +
                            "eg. event {event} /at {date/time}");
                case DELETE:
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! The delete command should be used as shown. " +
                            "eg. delete {num of task in list to be deleted.}");
                default:
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } else {
            switch (command) {
                case MARK:
                    if (userInput.length != 2) {
                        throw new DukeException(INDENTATION +
                                "☹ OOPS!!! The mark command should be used as shown. " +
                                "eg. mark {num of task in list to be marked as done}");
                    }
                    if (!userInput[1].matches("\\d+")) {
                        throw new DukeException(INDENTATION +
                                "☹ OOPS!!! The mark command should be used as shown. " +
                                "eg. mark {num of task in list to be marked as done}");
                    }
                    int indexToMark = Integer.parseInt(userInput[1]);
                    return new MarkCommand(indexToMark);
                case UNMARK:
                    if (userInput.length != 2) {
                        throw new DukeException(INDENTATION +
                                "☹ OOPS!!! The unmark command should be used as shown. " +
                                "eg. mark {num of task in list to be unmarked as incomplete}");
                    }
                    if (!userInput[1].matches("\\d+")) {
                        throw new DukeException(INDENTATION +
                                "☹ OOPS!!! The mark command should be used as shown. " +
                                "eg. mark {num of task in list to be marked as done}");
                    }
                    int indexToUnmark = Integer.parseInt(userInput[1]);
                    return new UnmarkCommand(indexToUnmark);
                case TODO:
                    String[] toDoDescription = Arrays.copyOfRange(userInput, 1, userInput.length);
                    return new ToDoCommand(String.join(" ", toDoDescription));
                case DEADLINE:
                    if (!Arrays.asList(userInput).contains("/by")) {
                        throw new DukeException(INDENTATION +
                                "☹ OOPS!!! Please use the deadline command in the correct manner, " +
                                "usage of deadline is as shown. " +
                                "eg. deadline {task to be done} /by {yyyy-mm-dd}");
                    }
                    int byIndex = Arrays.asList(userInput).indexOf("/by");
                    String[] deadlineDescription = Arrays.copyOfRange(userInput, 1, byIndex);
                    String[] by = Arrays.copyOfRange(userInput, byIndex + 1, userInput.length);
                    if (!String.join(" ", by).matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                        throw new DukeException(INDENTATION +
                                "☹ OOPS!!! Please use the deadline command in the correct manner, " +
                                "usage of deadline is as shown. " +
                                "eg. deadline {task to be done} /by {yyyy-mm-dd}");
                    }
                    return new DeadlineCommand(String.join(" ", deadlineDescription),
                            String.join(" ", by));
                case EVENT:
                    if (!Arrays.asList(userInput).contains("/at")) {
                        throw new DukeException(INDENTATION +
                                "☹ OOPS!!! Please use the event command in the correct manner, " +
                                "usage of deadline is as shown. " +
                                "eg. event {event} /at {yyyy-mm-dd}");
                    }
                    int atIndex = Arrays.asList(userInput).indexOf("/at");
                    String[] eventDescription = Arrays.copyOfRange(userInput, 1, atIndex);
                    String[] at = Arrays.copyOfRange(userInput, atIndex + 1, userInput.length);
                    if (!String.join(" ", at).matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                        throw new DukeException(INDENTATION +
                                "☹ OOPS!!! Please use the event command in the correct manner, " +
                                "usage of deadline is as shown. " +
                                "eg. event {event} /at {yyyy-mm-dd}");
                    }
                    return new EventCommand(String.join(" ", eventDescription),
                            String.join(" ", at));
                case DELETE:
                    if (userInput.length != 2) {
                        throw new DukeException(INDENTATION +
                                "☹ OOPS!!! The delete command should be used as shown. " +
                                "eg. delete {num of task in list to be deleted.}");
                    }
                    if (!userInput[1].matches("\\d+")) {
                        throw new DukeException(INDENTATION +
                                "☹ OOPS!!! The mark command should be used as shown. " +
                                "eg. mark {num of task in list to be marked as done}");
                    }
                    int indexToDelete = Integer.parseInt(userInput[1]);
                    return new DeleteCommand(indexToDelete);
                default:
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
