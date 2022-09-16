package duke.parser;
import duke.commands.*;

import duke.exceptions.*;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.tasks.Deadline;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Class that provides method to handle input for CLI.
 */
public class CLIParser {

    /**
     * Provides UserCommand from given input string and current tasks.
     * @param commandString Input String contain command.
     * @param userTasks TaskList of current tasks.
     * @return UserCommand formally.
     * @throws DukeException Exception may occur during this session, e.g. InvalidCommandException.
     */
    public UserCommand parseCommand(String commandString, TaskList userTasks) throws DukeException {
        int firstSpace = commandString.indexOf(" ");

        if (firstSpace == -1) {

            switch (commandString) {
                case "bye":
                    return new QuitCommand();
                case "list":
                    return new ListTasksCommand(userTasks);
                case "help":
                    return new HelpCommand();
                case "sort":
                    return new SortAllCommand(userTasks);
                case "save":
                    return new SaveCommand(userTasks);
                default:
                    throw new InvalidCommandException();
            }
        }

        String commandType = commandString.substring(0, firstSpace);
        String commandElse = commandString.substring(firstSpace).strip();


        switch (commandType) {

        case "todo" :
            return parseTodoCommand(commandElse, userTasks);
        case "deadline" :
            return parseDeadlineCommand(commandElse, userTasks);
        case "event" :
            return parseEventCommand(commandElse, userTasks);
        case "find":
            return parseFindTaskCommand(commandElse, userTasks);
        case "delete":
            return parseDeleteTaskCommand(commandElse, userTasks);
        case "done":
            return parseMarkDoneCommand(commandElse, userTasks);
        case "sort":
            return parseSortCommand(commandElse, userTasks);



        default:
            System.out.println("CLIParser .. default ...");


        }
        throw new InvalidCommandException();
    }
    private SortDeadlineCommand parseSortCommand(String taskName, TaskList userTasks) throws TaskNotExistException {
        if (taskName.equals("Deadline"))
            return new SortDeadlineCommand(userTasks);
        else throw new TaskNotExistException();
    }
    private AddTaskCommand parseTodoCommand(String taskName, TaskList userTasks) {
        return new AddTaskCommand(new ToDo(taskName, false), userTasks);
    }
    private AddTaskCommand parseDeadlineCommand(String commandElse, TaskList userTasks) throws DeadlineException {
        try {
            String[] parts = commandElse.split("/");
            String discription = parts[0].strip();
            String[] time = parts[1].strip().split(" ");

            if (time.length == 1) {
                LocalDate date = LocalDate.parse(time[0].strip());

                // Check if it is good deadline
                return new AddTaskCommand(
                        new Deadline(discription, date, false),
                        userTasks);
            } else {
                LocalDate date = LocalDate.parse(time[0].strip());
                LocalTime hourMinutes = LocalTime.parse(time[1].strip());

                // Check if it is good deadline
                return new AddTaskCommand(
                        new Deadline(discription, date, hourMinutes, false),
                        userTasks);
            }

        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DeadlineException();

        }

    }
    private AddTaskCommand parseEventCommand(String commandElse, TaskList userTasks) throws EventException {
        try {
            String[] parts = commandElse.split("/");
            String discription = parts[0].strip();
            String[] time = parts[1].strip().split(" ");

            return new AddTaskCommand(
                    new Event(discription,
                            LocalDate.parse(time[0]),
                            LocalTime.parse(time[1]),
                            false),
                    userTasks);
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new EventException();
        }
    }
    private FindTaskCommand parseFindTaskCommand(String commandElse, TaskList userTasks) {
        return new FindTaskCommand(userTasks, commandElse.strip());
    }
    private DeleteTaskCommand parseDeleteTaskCommand(String commandElse, TaskList userTasks) throws TaskIndexException {
        try {
            return new DeleteTaskCommand(Integer.valueOf(commandElse), userTasks);
        } catch (NumberFormatException e) {
            throw new TaskIndexException();
        }
    }
    private MarkDoneCommand parseMarkDoneCommand(String commandElse, TaskList userTasks) throws TaskIndexException {
        try {
            return new MarkDoneCommand(Integer.valueOf(commandElse), userTasks);
        } catch (NumberFormatException e) {
            throw new TaskIndexException();
        }
    }

}
