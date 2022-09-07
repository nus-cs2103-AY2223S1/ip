package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bocil.BocilException;
import executor.Executor;
import task.TaskList;

/**
 * Handles the processing of user input and commands Bocil to do specific tasks.
 */
public class Parser {
    /** Date patterns accepted by the parser. */
    private static final String[] DATE_PATTERNS = {
        "yyyy-M-d HH:mm", "yyyy/M/d HH:mm", "yyyy M d HH:mm",
        "d-M-yyyy HH:mm", "d/M/yyyy HH:mm", "d M yyyy HH:mm",
        "yyyy-MM-d HH:mm", "yyyy/MM/d HH:mm", "yyyy MM d HH:mm",
        "d-MM-yyyy HH:mm", "d/MM/yyyy HH:mm", "d MM yyyy HH:mm",
        "yyyy-MMM-d HH:mm", "yyyy/MMM/d HH:mm", "yyyy MMM d HH:mm",
        "d-MMM-yyyy HH:mm", "d/MMM/yyyy HH:mm", "d MMM yyyy HH:mm",
        "yyyy-MMMM-d HH:mm", "yyyy/MMMM/d HH:mm", "yyyy MMMM d HH:mm",
        "d-MMMM-yyyy HH:mm", "d/MMMM/yyyy HH:mm", "d MMMM yyyy HH:mm",
        "yyyy-M-dd HH:mm", "yyyy/M/dd HH:mm", "yyyy M dd HH:mm",
        "dd-M-yyyy HH:mm", "dd/M/yyyy HH:mm", "dd M yyyy HH:mm",
        "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm", "yyyy MM dd HH:mm",
        "dd-MM-yyyy HH:mm", "dd/MM/yyyy HH:mm", "dd MM yyyy HH:mm",
        "yyyy-MMM-dd HH:mm", "yyyy/MMM/dd HH:mm", "yyyy MMM dd HH:mm",
        "dd-MMM-yyyy HH:mm", "dd/MMM/yyyy HH:mm", "dd MMM yyyy HH:mm",
        "yyyy-MMMM-dd HH:mm", "yyyy/MMMM/dd HH:mm", "yyyy MMMM dd HH:mm",
        "dd-MMMM-yyyy HH:mm", "dd/MMMM/yyyy HH:mm", "dd MMMM yyyy HH:mm"
    };
    private final Executor executor;
    private final TaskList taskList;

    /**
     * Constructs a dummy {@link Parser} object to parse datetime.
     */
    public Parser() {
        this.taskList = new TaskList();
        this.executor = new Executor(taskList);
    }

    /**
     * Constructs a {@link Parser} object.
     *
     * @param taskList {@link TaskList} object to modify.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
        this.executor = new Executor(taskList);
    }

    /**
     * Reads user input and commands the {@link Executor} to do actions on the {@link TaskList} object.
     *
     * @param input String line that the user inputs.
     * @return Response line of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String processInput(String input) throws BocilException {
        String[] split = input.split("\\s+", 2);
        if (input.matches("\\s*")) {
            throw BocilException.bocilEmptyInputException();
        }

        String command = split[0];
        String response;
        switch (command) {
        case "todo":
            response = this.processTodo(split);
            break;
        case "deadline": case "event":
            response = this.processDeadlineEvent(split);
            break;
        case "delete":
            response = this.executor.deleteTaskFromList(split);
            break;
        case "mark":
            response = this.executor.markAsDone(split);
            break;
        case "unmark":
            response = this.executor.unmarkAsDone(split);
            break;
        case "tag":
            response = this.executor.tagTask(split);
            break;
        case "untag":
            response = this.executor.untagTask(split);
            break;
        case "view":
            response = this.executor.viewTag(split);
            break;
        case "find":
            response = this.executor.findTaskFromList(split);
            break;
        case "list":
            response = this.executor.showList(input);
            break;
        case "bye":
            response = this.executor.endProgram(input);
            break;
        default:
            throw BocilException.bocilUnknownCommandException();
        }
        return response;
    }

    public String processTodo(String[] split) throws BocilException {
        String response;
        if (split.length < 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        String taskDetails = split[1];
        String[] tagDetails = taskDetails.split("\\s+/tag\\s+");
        String name = tagDetails[0];
        boolean noTag = tagDetails.length == 1;
        boolean haveTag = tagDetails.length == 2;
        boolean containsTagCommand = taskDetails.contains("/tag");
        if (noTag && containsTagCommand) {
            throw BocilException.bocilInvalidFormatException();
        }
        if (haveTag) {
            String tag = tagDetails[1];
            if (isValid(tag)) {
                response = this.executor.addNewTodo(name, tag);
            } else {
                throw BocilException.bocilInvalidTagFormatException();
            }
        } else if (noTag) {
            response = this.executor.addNewTodo(name);
        } else {
            throw BocilException.bocilInvalidFormatException();
        }
        return response;
    }

    public String processDeadlineEvent(String[] split) throws BocilException {
        String command = split[0];
        String response;
        if (split.length < 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        String taskDetails = split[1];
        String[] timeTagDetails;
        if (command.equals("deadline")) {
            timeTagDetails = taskDetails.split("\\s+/by\\s+");
        } else {
            timeTagDetails = taskDetails.split("\\s+/at\\s+");
        }
        if (timeTagDetails.length != 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        String name = timeTagDetails[0];
        String[] tagDetails = timeTagDetails[1].split("\\s+/tag\\s+");
        LocalDateTime time = this.parseTime(tagDetails[0]);
        boolean noTag = tagDetails.length == 1;
        boolean haveTag = tagDetails.length == 2;
        boolean containsTagCommand = taskDetails.contains("/tag");
        if (noTag && containsTagCommand) {
            throw BocilException.bocilInvalidFormatException();
        }
        if (haveTag) {
            String tag = tagDetails[1];
            if (isValid(tag)) {
                response = this.executor.addNewDeadlineEvent(command, name, time, tag);
            } else {
                throw BocilException.bocilInvalidTagFormatException();
            }
        } else if (noTag) {
            response = this.executor.addNewDeadlineEvent(command, name, time);
        } else {
            throw BocilException.bocilInvalidFormatException();
        }
        return response;
    }

    /**
     * Checks if a tag name is valid.
     *
     * @param tag Tag name to be checked.
     * @return Validity of the tag.
     */
    public boolean isValid(String tag) {
        return !tag.contains(" ");
    }

    /**
     * Converts date from user input into a {@link LocalDateTime} object.
     *
     * @param date Date string that the user inputs.
     * @return Date in the form of {@link LocalDateTime}.
     * @throws BocilException If inputted date is not of the accepted format.
     */
    public LocalDateTime parseTime(String date) throws BocilException {
        for (String pattern: DATE_PATTERNS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDateTime.parse(date, formatter);
            } catch (DateTimeParseException e) {
                // Proceeds to the next format if the current one does not match
            }
        }
        throw BocilException.bocilInvalidDateFormatException();
    }
}
