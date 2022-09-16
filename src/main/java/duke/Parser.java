package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkStatusCommand;
import duke.command.SortCommand;
import duke.command.UnmarkStatusCommand;
import duke.exceptions.BlankCommandException;
import duke.exceptions.BlankDescriptionException;
import duke.exceptions.CannotSortException;
import duke.exceptions.CommandNotFoundException;
import duke.exceptions.DukeException;
import duke.exceptions.ImproperDeadlineFormatException;
import duke.exceptions.ImproperEventFormatException;
import duke.exceptions.InvalidNumberException;
import duke.exceptions.InvalidTaskException;
import duke.exceptions.NoDateException;

/**
 * Represents a parser to understand instruction coming from user.
 * Reads user input and outputs the relevant command.
 */
public class Parser {
    /**
     * Returns interpreted command of user input.
     *
     * @param input User input.
     * @return interpreted Command.
     * @throws BlankCommandException If input is blank.
     * @throws BlankDescriptionException If description is blank
     *                                   when user is inputting
     *                                   complex instruction.
     */
    public static Command parse(String input) throws DukeException {
        if (input.isBlank()) {
            throw new BlankCommandException();
        }
        String[] inputArr = input.split(" ", 2);
        // inputArr: ["keyword", "description"]
        String keyword = inputArr[0];
        if (inputArr.length < 2) {
            return parseSimpleCommand(keyword);
        }
        if (inputArr[1].isBlank()) {
            throw new BlankDescriptionException();
        }
        String description = inputArr[1];
        return parseComplexCommand(keyword, description);
    }

    /**
     * Returns interpreted command of user input.
     * SimpleCommand is consist of only keyword with no description.
     *
     * @param keyword User input.
     * @return interpreted Command.
     * @throws CommandNotFoundException If input is not in list of keywords.
     */
    private static Command parseSimpleCommand(String keyword) throws CommandNotFoundException {
        switch (keyword) {
        case ("bye"):
            return new ExitCommand();
        case ("list"):
            return new ListCommand();
        default:
            throw new CommandNotFoundException(keyword);
        }
    }

    /**
     * Returns interpreted command of user input.
     * ComplexCommand consist of keyword and description
     *
     * @param keyword User input.
     * @param description Task description
     * @return interpreted Command.
     * @throws DukeException If user's input does not match valid commands.
     */
    private static Command parseComplexCommand(String keyword, String description) throws DukeException {
        switch (keyword) {
        case ("find"):
            return new FindCommand(description);
        case ("todo"):
        case ("deadline"):
        case ("event"):
            return parseAddCommand(TypeOfTask.validate(keyword), description);
        case ("sort"):
            // description: "(deadline/ event) (chrono/ lexi) (increasing/ decreasing)"
            String[] sortArr = description.split(" ", 3);
            // sortArr: ["(deadline/ event)", "chrono/ lexi)", "increasing/ decreasing)"]
            if (sortArr.length < 3) {
                throw new ImproperEventFormatException();
            }
            return parseSortCommand(TypeOfTask.validate(sortArr[0]),
                    Order.validate(sortArr[1]),
                    Order.validate(sortArr[2]));
        case ("mark"):
            try {
                int x = Integer.valueOf(description);
                return new MarkStatusCommand(x);
            } catch (NumberFormatException e) {
                throw new InvalidNumberException();
            }
        case ("unmark"):
            try {
                int x = Integer.valueOf(description);
                return new UnmarkStatusCommand(x);
            } catch (NumberFormatException e) {
                throw new InvalidNumberException();
            }
        case ("delete"):
            try {
                int x = Integer.valueOf(description);
                return new DeleteCommand(x);
            } catch (NumberFormatException e) {
                throw new InvalidNumberException();
            }
        default:
            throw new CommandNotFoundException(keyword);
        }
    }

    /**
     * Returns interpreted command of user to add a task to taskList.
     *
     * @param task The task to be added to the taskList.
     * @param description The description of the task to be added.
     * @return AddCommand.
     * @throws ImproperDeadlineFormatException If description does not
     *                                         follow specified format.
     * @throws ImproperEventFormatException If description does not
     *                                      follow specified format.
     * @throws NoDateException If description of Deadline or Event
     *                         does not consist of date.
     * @throws InvalidTaskException If Task to be added does not exist within
     *                              the specified TypeOfTask
     */
    private static AddCommand parseAddCommand(TypeOfTask task, String description)
            throws ImproperDeadlineFormatException,
            ImproperEventFormatException,
            NoDateException,
            InvalidTaskException {
        if (task == TypeOfTask.todo) {
            ToDo toDo = new ToDo(description);
            return new AddCommand(toDo);
        }
        if (task == TypeOfTask.deadline) {
            // description: "(deadlineTask) /by (by)"
            String[] deadlineArr = description.split(" /by", 2);
            // deadlineArr: ["deadlineTask", " by"]
            if (deadlineArr.length == 1) {
                throw new ImproperDeadlineFormatException();
            }
            String deadlineTask = deadlineArr[0];
            String by = deadlineArr[1];
            if (by.isBlank()) {
                throw new NoDateException();
            }
            Deadline deadline = new Deadline(deadlineTask, by);
            return new AddCommand(deadline);
        }
        if (task == TypeOfTask.event) {
            // description: "(EventTask) /at (at)"
            String[] eventArr = description.split(" /at", 2);
            // eventArr: ["eventTask", "at"]
            if (eventArr.length == 1) {
                throw new ImproperEventFormatException();
            }
            String evenTask = eventArr[0];
            String at = eventArr[1];
            if (at.isBlank()) {
                throw new NoDateException();
            }
            Event event = new Event(evenTask, at);
            return new AddCommand(event);
        }
        throw new InvalidTaskException(task.toString());
    }

    /**
     * Returns interpreted command of user to add a task to taskList.
     *
     * @param task The task to be added to the taskList.
     * @param typeOfOrder either chronological or lexicographical order.
     * @param order either increasing or decreasing.
     * @return SortCommand.
     * @throws CannotSortException when specified task to be sorted cannot be sorted.
     */
    private static Command parseSortCommand(
            TypeOfTask task,
            Order typeOfOrder,
            Order order) throws CannotSortException {
        if (typeOfOrder == Order.chrono) {
            return new SortCommand(task, order, true);
        }
        if (typeOfOrder == Order.lexi) {
            return new SortCommand(task, order, false);
        }
        throw new CannotSortException(typeOfOrder
                + " IS NOT A SUPPORTED SORTING ORDER");
    }
}
