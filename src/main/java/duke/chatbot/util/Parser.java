package duke.chatbot.util;

import static duke.chatbot.common.DateFormat.DATE_TIME_INPUT_FORMAT;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.chatbot.command.AddDeadlineCommand;
import duke.chatbot.command.AddEventCommand;
import duke.chatbot.command.AddToDoCommand;
import duke.chatbot.command.CheckDateCommand;
import duke.chatbot.command.Command;
import duke.chatbot.command.DeleteCommand;
import duke.chatbot.command.ExitCommand;
import duke.chatbot.command.FindCommand;
import duke.chatbot.command.ListCommand;
import duke.chatbot.command.MarkCommand;
import duke.chatbot.command.UnmarkCommand;
import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.exception.InvalidStorageFileException;
import duke.chatbot.data.task.Deadline;
import duke.chatbot.data.task.Event;
import duke.chatbot.data.task.TaskList;
import duke.chatbot.data.task.ToDo;

/**
 * A parser class to extract information from strings.
 * @author jq1836
 */
public class Parser {
    /**
     * Returns an instance of Command, which contains all the arguments
     * of the command string parsed in an instance of list of string.
     * @param line The string to be parsed.
     * @return An instance of Command which corresponds to the command
     *     string parsed.
     * @throws InvalidInputException If line is not a valid command string.
     */
    public static Command parseCommand(String line) throws InvalidInputException {
        if (line == null) {
            throw new InvalidInputException();
        } else if (line.equals("bye")) {
            return new ExitCommand();
        } else if (line.equals("list")) {
            return new ListCommand();
        } else {
            Scanner sc = new Scanner(line);
            List<String> arguments = new ArrayList<>();

            String caseString = sc.next();
            if ((caseString.equals("mark") || caseString.equals("unmark") || caseString.equals("delete"))
                    && sc.hasNextInt()) {
                arguments.add(sc.next());
                if (!sc.hasNext()) {
                    if (caseString.equals("mark")) {
                        return new MarkCommand(arguments);
                    } else if (caseString.equals("unmark")) {
                        return new UnmarkCommand(arguments);
                    } else {
                        return new DeleteCommand(arguments);
                    }
                }
            } else if (caseString.equals("find") && sc.hasNext()) {
                arguments.add(sc.nextLine().substring(1));
                return new FindCommand(arguments);
            } else if (caseString.equals("todo") && sc.hasNext()) {
                arguments.add(sc.nextLine().substring(1));
                return new AddToDoCommand(arguments);
            } else if (caseString.equals("deadline") && sc.hasNext()) {
                sc.useDelimiter(" /by ");
                arguments.add(sc.next().substring(1));
                if (sc.hasNext()) {
                    arguments.add(sc.next());
                    if (!sc.hasNext()) {
                        return new AddDeadlineCommand(arguments);
                    }
                }
            } else if (caseString.equals("event") && sc.hasNext()) {
                sc.useDelimiter(" /at ");
                arguments.add(sc.next().substring(1));
                if (sc.hasNext()) {
                    arguments.add(sc.next());
                    if (!sc.hasNext()) {
                        return new AddEventCommand(arguments);
                    }
                }
            } else if (caseString.equals("check") && sc.hasNext()) {
                arguments.add(sc.nextLine().substring(1));
                return new CheckDateCommand(arguments);
            }
            throw new InvalidInputException();
        }
    }

    /**
     * Returns an instance of LocalDateTime that corresponds to the
     * string parsed.
     * @param dateTime A string containing date and time information.
     * @return An instance of LocalDateTime that corresponds to the
     *     string parsed.
     * @throws InvalidInputException If the argument string does not
     *     follow the format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws InvalidInputException {
        try {
            return LocalDateTime.parse(dateTime, DATE_TIME_INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException();
        }
    }

    /**
     * Returns a list of tasks that was stored in the file.
     * @param file A file that contains a list of tasks.
     * @return A list of tasks that was stored in the file.
     * @throws FileNotFoundException If a file is not found.
     * @throws InvalidInputException If the date and time portion
     *     of the encoded task is not in the correct format.
     */
    public static TaskList parseFile(File file) throws FileNotFoundException, InvalidInputException {
        Scanner fileScanner = new Scanner(file);
        TaskList result = new TaskList();

        while (fileScanner.hasNext()) {
            Scanner lineScanner = new Scanner(fileScanner.nextLine());
            lineScanner.useDelimiter(",,,");
            String category = lineScanner.next();

            if (!lineScanner.hasNextInt()) {
                throw new InvalidStorageFileException();
            }
            boolean isDone = lineScanner.nextInt() == 1;
            if (!lineScanner.hasNext()) {
                throw new InvalidStorageFileException();
            }
            String description = lineScanner.next();

            if (category.equals("D") && lineScanner.hasNext()) {
                result.add(new Deadline(description, parseDateTime(lineScanner.next()), isDone));
            } else if (category.equals("E") && lineScanner.hasNext()) {
                result.add(new Event(description, parseDateTime(lineScanner.next()), isDone));
            } else if (category.equals("T")) {
                result.add(new ToDo(description, isDone));
            } else {
                throw new InvalidStorageFileException();
            }

            if (lineScanner.hasNext()) {
                throw new InvalidStorageFileException();
            }
        }
        return result;
    }
}
