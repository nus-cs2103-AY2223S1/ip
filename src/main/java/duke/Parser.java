package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DateCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.command.UpdateCommand;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Parser deals with making sense of the user command.
 */
public class Parser {
    private String cmd;
    private Optional<String> task;

    /**
     * Constructor for Parser.
     *
     * @param str String given by user input.
     */
    public Parser(String str) {
        String[] split = str.split(" ", 2);
        this.cmd = split[0];
        if (split.length > 1 && !split[1].equals("")) {
            this.task = Optional.of(split[1]);
        } else {
            this.task = Optional.empty();
        }
    }

    public int getTaskNumber() throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("Error! No task found.");
        } else {
            int num = Integer.parseInt(task.get()) - 1;
            assert num >= 0 : "Input should be larger than 0";
            return num;
        }
    }

    public String getEventTask() throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("Description of an event not found. Please provide one.");
        } else {
            return task.get().split(" /at ", 2)[0];
        }
    }

    public String getDeadlineTask() throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("Description of a deadline not found. Please provide one.");
        } else {
            return task.get().split(" /by ", 2)[0];
        }
    }

    public String getToDoTask() throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("Description of ToDo not found. Please provide one.");
        } else {
            return task.get();
        }
    }

    public LocalDate getEventTime() throws DukeException {
        try {
            if (task.isEmpty()) {
                throw new DukeException("Description of an event not found. Please provide one.");
            } else {
                String[] splitEvent = task.get().split(" /at ", 2);
                if (splitEvent.length == 1) {
                    throw new DukeException("Time of event not found. Please provide a time.");
                } else {
                    return LocalDate.parse(splitEvent[1]);
                }
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter time in YYYY-MM-DD format.");
        }

    }

    public LocalDate getDeadlineTime() throws DukeException {
        try {
            if (task.isEmpty()) {
                throw new DukeException("Description of deadline not found. Please provide one.");
            } else {
                String[] splitEvent = task.get().split(" /by ", 2);
                if (splitEvent.length == 1) {
                    throw new DukeException("Time of deadline not found. Please provide a time.");
                } else {
                    return LocalDate.parse(splitEvent[1]);
                }
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter time in YYYY-MM-DD format.");
        }
    }

    public LocalDate getDate() throws DukeException {
        try {
            if (task.isEmpty()) {
                throw new DukeException("Date to be found is empty. Please provide a valid date.");
            } else {
                return LocalDate.parse(task.get());
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter date in YYYY-MM-DD format");
        }
    }

    public String getFindWord() throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("No keyword found. Please enter a keyword.");
        } else {
            return task.get();
        }
    }

    public int getUpdateNum() throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("Error! No task found.");
        } else {
            return Integer.parseInt(task.get().split(" /update ", 2)[0]) - 1;
        }
    }

    public String getUpdate() throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("Error! No task found.");
        } else {
            String[] splitTask = task.get().split(" /update ", 2);
            if (splitTask.length == 1) {
                throw new DukeException("Update not found. Please provide an update.");
            } else {
                return splitTask[1];
            }
        }
    }

    public Command getCommand() throws DukeException {
        if (cmd.equals("mark")) {
            return new MarkCommand(getTaskNumber());
        } else if (cmd.equals("unmark")) {
            return new UnmarkCommand(getTaskNumber());
        } else if (cmd.equals("bye")) {
            return new ByeCommand();
        } else if (cmd.equals("list")) {
            return new ListCommand();
        } else if (cmd.equals("todo")) {
            return new ToDoCommand(getToDoTask());
        } else if (cmd.equals("event")) {
            return new EventCommand(getEventTask(), getEventTime());
        } else if (cmd.equals("deadline")) {
            return new DeadlineCommand(getDeadlineTask(), getDeadlineTime());
        } else if (cmd.equals("delete")) {
            return new DeleteCommand(getTaskNumber());
        } else if (cmd.equals("date")) {
            return new DateCommand(getDate());
        } else if (cmd.equals("find")) {
            return new FindCommand(getFindWord());
        } else if (cmd.equals("update")) {
            return new UpdateCommand(getUpdateNum(), getUpdate());
        } else {
            throw new DukeException("Unknown command. Please enter a valid command");
        }
    }
}
