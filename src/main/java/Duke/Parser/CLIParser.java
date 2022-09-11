package Duke.Parser;

import Duke.Commands.*;


import Duke.Exceptions.*;
import Duke.Tasks.Event;
import Duke.Tasks.TaskList;
import Duke.Tasks.ToDo;
import Duke.Tasks.Deadline;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * CLIParser
 * Acts as a class to interpret user commands.
 */
public class CLIParser {

    // Like a classifier
    public UserCommand parseCommand(String commandString, TaskList userTasks) throws DukeException {
        int firstSpace = commandString.indexOf(" ");

        if (firstSpace == -1) {
            if (commandString.equals("bye")) {
                return new QuitCommand();
            }

            if (commandString.equals("list")) {
                return new ListTasksCommand(userTasks);
            }

            if (commandString.equals("help")) {
                return new HelpCommand();
            }

            if (commandString.equals("sort")) {
                return new SortAllCommand(userTasks);
            }

            throw new InvalidCommandException();
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
    private AddTaskCommand parseDeadlineCommand(String commandElse, TaskList userTasks) throws DukeException {
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
            throw new DateTimeException();

        }

    }
    private AddTaskCommand parseEventCommand(String comandElse, TaskList userTasks) throws DukeException {
        try {
            String[] parts = comandElse.split("/");
            String discription = parts[0].strip();
            String[] time = parts[1].strip().split(" ");

            return new AddTaskCommand(
                    new Event(discription,
                            LocalDate.parse(time[0]),
                            LocalTime.parse(time[1]),
                            false),
                    userTasks);
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new DateTimeException();
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
