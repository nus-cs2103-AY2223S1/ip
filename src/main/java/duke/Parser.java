package duke;

import java.util.Arrays;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.PriorityCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;


/**
 * Parser parses and helps to make sense of user input.
 */
public class Parser {
    private enum Commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, PRIORITY
    }

    /**
     * Returns a command to carry out based on the user input.
     *
     * @param fullCommand user input.
     * @return Command to carry out.
     * @throws DukeException
     */
    public static Command parse(String fullCommand) throws DukeException {
        Commands command;
        String[] userInputs = fullCommand.trim().split(" ");
        boolean isUserInputsLengthOne = userInputs.length == 1;
        boolean isNotUserInputsLengthTwo = userInputs.length != 2;
        boolean isNotUserInputsLengthThree = userInputs.length != 3;
        boolean hasNoBy = !Arrays.asList(userInputs).contains("/by");
        boolean hasNoAt = !Arrays.asList(userInputs).contains("/at");

        try {
            command = Commands.valueOf(userInputs[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }

        if (isUserInputsLengthOne) {
            switch (command) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                throw new DukeException("OOPS!!! The mark command should be used as shown. "
                        + "eg. mark {num of task in list to be marked as done}");
            case UNMARK:
                throw new DukeException("OOPS!!! The unmark command should be used as shown. "
                        + "eg. mark {num of task in list to be unmarked as incomplete}");
            case TODO:
                throw new DukeException("OOPS!!! The description of a todo cannot be empty, "
                        + "usage of todo is as shown. eg. todo {task to be done}");
            case DEADLINE:
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty, "
                        + "usage of deadline is as shown. "
                        + "eg. deadline {task to be done} /by {date/time to complete}");
            case EVENT:
                throw new DukeException("OOPS!!! The description of a event cannot be empty, "
                        + "usage of event is as shown. "
                        + "eg. event {event} /at {date/time}");
            case DELETE:
                throw new DukeException("OOPS!!! The delete command should be used as shown. "
                        + "eg. delete {num of task in list to be deleted.}");
            case FIND:
                throw new DukeException("OOPS!!! The find command should be used as shown. "
                        + "eg. find {keyword to search}");
            case PRIORITY:
                throw new DukeException("OOPS!!! The priority command should be used as shown. "
                        + "eg. priority {num of task in list to change priority} {low/medium/high}");
            default:
                throw new InvalidCommandException();
            }
        } else {
            boolean isNotIndexOfTask = !userInputs[1].matches("\\d+");
            switch (command) {
            case MARK:
                if (isNotUserInputsLengthTwo) {
                    throw new DukeException("OOPS!!! The mark command should be used as shown. "
                            + "eg. mark {num of task in list to be marked as done}");
                }
                if (isNotIndexOfTask) {
                    throw new InvalidIndexException();
                }

                int indexToMark = Integer.parseInt(userInputs[1]);
                return new MarkCommand(indexToMark);
            case UNMARK:
                if (isNotUserInputsLengthTwo) {
                    throw new DukeException("OOPS!!! The unmark command should be used as shown. "
                            + "eg. mark {num of task in list to be unmarked as incomplete}");
                }
                if (isNotIndexOfTask) {
                    throw new InvalidIndexException();
                }

                int indexToUnmark = Integer.parseInt(userInputs[1]);
                return new UnmarkCommand(indexToUnmark);
            case TODO:
                String[] toDoDescription = Arrays.copyOfRange(userInputs, 1, userInputs.length);
                return new ToDoCommand(String.join(" ", toDoDescription));
            case DEADLINE:
                if (hasNoBy) {
                    throw new DukeException("OOPS!!! Please use the deadline command in the correct manner, "
                            + "usage of deadline is as shown. "
                            + "eg. deadline {task to be done} /by {yyyy-mm-dd}");
                }

                int byIndex = Arrays.asList(userInputs).indexOf("/by");
                String[] deadlineDescription = Arrays.copyOfRange(userInputs, 1, byIndex);
                String[] by = Arrays.copyOfRange(userInputs, byIndex + 1, userInputs.length);

                boolean hasNoValidDeadlineDate = !String.join(" ", by).matches("^\\d{4}-\\d{2}-\\d{2}$");
                if (hasNoValidDeadlineDate) {
                    throw new DukeException("OOPS!!! Please use the deadline command in the correct manner, "
                            + "usage of deadline is as shown. "
                            + "eg. deadline {task to be done} /by {yyyy-mm-dd}");
                }

                return new DeadlineCommand(String.join(" ", deadlineDescription),
                        String.join(" ", by));
            case EVENT:
                if (hasNoAt) {
                    throw new DukeException("OOPS!!! Please use the event command in the correct manner, "
                            + "usage of deadline is as shown. "
                            + "eg. event {event} /at {yyyy-mm-dd}");
                }

                int atIndex = Arrays.asList(userInputs).indexOf("/at");
                String[] eventDescription = Arrays.copyOfRange(userInputs, 1, atIndex);
                String[] at = Arrays.copyOfRange(userInputs, atIndex + 1, userInputs.length);

                boolean hasNoValidEventDate = !String.join(" ", at).matches("^\\d{4}-\\d{2}-\\d{2}$");
                if (hasNoValidEventDate) {
                    throw new DukeException("OOPS!!! Please use the event command in the correct manner, "
                            + "usage of deadline is as shown. "
                            + "eg. event {event} /at {yyyy-mm-dd}");
                }

                return new EventCommand(String.join(" ", eventDescription),
                        String.join(" ", at));
            case DELETE:
                if (isNotUserInputsLengthTwo) {
                    throw new DukeException("OOPS!!! The delete command should be used as shown. "
                            + "eg. delete {num of task in list to be deleted.}");
                }
                if (isNotIndexOfTask) {
                    throw new InvalidIndexException();
                }

                int indexToDelete = Integer.parseInt(userInputs[1]);
                return new DeleteCommand(indexToDelete);
            case FIND:
                String[] searchKeywords = Arrays.copyOfRange(userInputs, 1, userInputs.length);
                return new FindCommand(String.join(" ", searchKeywords));
            case PRIORITY:
                if (isNotUserInputsLengthThree) {
                    throw new DukeException("OOPS!!! The priority command should be used as shown. "
                            + "eg. priority {num of task in list to change priority} {low/medium/high}");
                }
                if (isNotIndexOfTask) {
                    throw new InvalidIndexException();
                }

                boolean isNotLowPriority = !userInputs[2].trim().matches("\\blow\\b");
                boolean isNotMediumPriority = !userInputs[2].trim().matches("\\bmedium\\b");
                boolean isNotHighPriority = !userInputs[2].trim().matches("\\bhigh\\b");
                if (isNotLowPriority && isNotMediumPriority && isNotHighPriority) {
                    throw new DukeException("OOPS!!! The priority command should be used as shown. "
                            + "eg. priority {num of task in list to change priority} {low/medium/high}");
                }

                int indexToChangePriority = Integer.parseInt(userInputs[1]);
                String priority = userInputs[2].toUpperCase();
                return new PriorityCommand(indexToChangePriority, priority);
            default:
                throw new InvalidCommandException();
            }
        }
    }
}
