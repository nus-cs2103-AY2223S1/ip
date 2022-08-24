package duke.chatbot.parser;

import duke.chatbot.command.*;
import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.exception.InvalidTaskFileException;
import duke.chatbot.data.task.Deadline;
import duke.chatbot.data.task.Event;
import duke.chatbot.data.task.TaskList;
import duke.chatbot.data.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public static final DateTimeFormatter DATE_TIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

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

    public static LocalDateTime parseDateTime(String dateTime) throws InvalidInputException {
        try {
            return LocalDateTime.parse(dateTime, DATE_TIME_INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException();
        }
    }

    public static TaskList parseFile(File file) throws FileNotFoundException, InvalidInputException {
        Scanner fileScanner = new Scanner(file);
        TaskList result = new TaskList();

        while (fileScanner.hasNext()) {
            Scanner lineScanner = new Scanner(fileScanner.nextLine());
            lineScanner.useDelimiter(",,,");
            String category = lineScanner.next();

            if (!lineScanner.hasNextInt()) {
                throw new InvalidTaskFileException();
            }

            boolean isDone = lineScanner.nextInt() == 1;

            if (!lineScanner.hasNext()) {
                throw new InvalidTaskFileException();
            }

            String description = lineScanner.next();

            if (category.equals("D") && lineScanner.hasNext()) {
                result.add(new Deadline(description, parseDateTime(lineScanner.next()), isDone));
            } else if (category.equals("E") && lineScanner.hasNext()) {
                result.add(new Event(description, parseDateTime(lineScanner.next()), isDone));
            } else if (category.equals("T")) {
                result.add(new ToDo(description, isDone));
            } else {
                throw new InvalidTaskFileException();
            }

            if (lineScanner.hasNext()) {
                throw new InvalidTaskFileException();
            }
        }
        return result;
    }
}
