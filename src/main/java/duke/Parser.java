package duke;

import duke.command.*;

import java.util.Arrays;

/**
 * Parser parses and helps to make sense of user input.
 */
public class Parser {
    private final static String INDENTATION = "   ";
    private enum Commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND
    }

    /**
     * Returns a command to carry out based on the user input.
     * @param fullCommand user input.
     * @return Command to carry out.
     * @throws DukeException
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] userInputs = fullCommand.trim().split(" ");
        Commands command = Commands.valueOf(userInputs[0].toUpperCase());
        if (userInputs.length == 1) {
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
            case FIND:
                throw new DukeException(INDENTATION +
                        "☹ OOPS!!! The find command should be used as shown. " +
                        "eg. find {keyword to search}");
            default:
                throw new DukeException(INDENTATION +
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } else {
            switch (command) {
            case MARK:
                if (userInputs.length != 2) {
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! The mark command should be used as shown. " +
                            "eg. mark {num of task in list to be marked as done}");
                }
                if (!userInputs[1].matches("\\d+")) {
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! The mark command should be used as shown. " +
                            "eg. mark {num of task in list to be marked as done}");
                }
                int indexToMark = Integer.parseInt(userInputs[1]);
                return new MarkCommand(indexToMark);
            case UNMARK:
                if (userInputs.length != 2) {
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! The unmark command should be used as shown. " +
                            "eg. mark {num of task in list to be unmarked as incomplete}");
                }
                if (!userInputs[1].matches("\\d+")) {
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! The mark command should be used as shown. " +
                            "eg. mark {num of task in list to be marked as done}");
                }
                int indexToUnmark = Integer.parseInt(userInputs[1]);
                return new UnmarkCommand(indexToUnmark);
            case TODO:
                String[] toDoDescription = Arrays.copyOfRange(userInputs, 1, userInputs.length);
                return new ToDoCommand(String.join(" ", toDoDescription));
            case DEADLINE:
                if (!Arrays.asList(userInputs).contains("/by")) {
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! Please use the deadline command in the correct manner, " +
                            "usage of deadline is as shown. " +
                            "eg. deadline {task to be done} /by {yyyy-mm-dd}");
                }
                int byIndex = Arrays.asList(userInputs).indexOf("/by");
                String[] deadlineDescription = Arrays.copyOfRange(userInputs, 1, byIndex);
                String[] by = Arrays.copyOfRange(userInputs, byIndex + 1, userInputs.length);
                if (!String.join(" ", by).matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! Please use the deadline command in the correct manner, " +
                            "usage of deadline is as shown. " +
                            "eg. deadline {task to be done} /by {yyyy-mm-dd}");
                }
                return new DeadlineCommand(String.join(" ", deadlineDescription),
                        String.join(" ", by));
            case EVENT:
                if (!Arrays.asList(userInputs).contains("/at")) {
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! Please use the event command in the correct manner, " +
                            "usage of deadline is as shown. " +
                            "eg. event {event} /at {yyyy-mm-dd}");
                }
                int atIndex = Arrays.asList(userInputs).indexOf("/at");
                String[] eventDescription = Arrays.copyOfRange(userInputs, 1, atIndex);
                String[] at = Arrays.copyOfRange(userInputs, atIndex + 1, userInputs.length);
                if (!String.join(" ", at).matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! Please use the event command in the correct manner, " +
                            "usage of deadline is as shown. " +
                            "eg. event {event} /at {yyyy-mm-dd}");
                }
                return new EventCommand(String.join(" ", eventDescription),
                        String.join(" ", at));
            case DELETE:
                if (userInputs.length != 2) {
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! The delete command should be used as shown. " +
                            "eg. delete {num of task in list to be deleted.}");
                }
                if (!userInputs[1].matches("\\d+")) {
                    throw new DukeException(INDENTATION +
                            "☹ OOPS!!! The mark command should be used as shown. " +
                            "eg. mark {num of task in list to be marked as done}");
                }
                int indexToDelete = Integer.parseInt(userInputs[1]);
                return new DeleteCommand(indexToDelete);
            case FIND:
                String[] searchKeywords = Arrays.copyOfRange(userInputs, 1, userInputs.length);
                return new FindCommand(String.join(" ", searchKeywords));
            default:
                throw new DukeException(INDENTATION +
                        "☹ OOPS!!! The mark command should be used as shown. " +
                        "eg. mark {num of task in list to be marked as done}");
                }
        }
    }
}
