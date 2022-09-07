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
     * Reads user input and runs the appropriate process method to get a response from Bocil.
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
            response = this.processDelete(split);
            break;
        case "mark":
            response = this.processMark(split);
            break;
        case "unmark":
            response = this.processUnmark(split);
            break;
        case "tag":
            response = this.processTag(split);
            break;
        case "untag":
            response = this.processUntag(split);
            break;
        case "view":
            response = this.processView(split);
            break;
        case "find":
            response = this.processFind(split);
            break;
        case "list":
            response = this.processList(split);
            break;
        case "bye":
            response = this.processBye(split);
            break;
        default:
            throw BocilException.bocilUnknownCommandException();
        }
        return response;
    }

    /**
     * Processes todo commands to get a response from Bocil.
     *
     * @param split The user's input split by each space.
     * @return Response line of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
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

    /**
     * Processes deadline and event commands to get a response from Bocil.
     *
     * @param split The user's input split by each space.
     * @return Response line of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
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
     * Processes delete commands to get a response from Bocil.
     *
     * @param split The user's input split by each space.
     * @return Response line of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String processDelete(String[] split) throws BocilException {
        String response;
        if (split.length < 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        try {
            int num = Integer.parseInt(split[1]);
            response = this.executor.deleteTaskFromList(num);
        } catch (NumberFormatException e) {
            throw BocilException.bocilInvalidIndexException();
        }
        return response;
    }

    /**
     * Processes mark commands to get a response from Bocil.
     *
     * @param split The user's input split by each space.
     * @return Response line of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String processMark(String[] split) throws BocilException {
        String response;
        if (split.length < 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        try {
            int num = Integer.parseInt(split[1]);
            response = this.executor.markAsDone(num);
        } catch (NumberFormatException e) {
            throw BocilException.bocilInvalidIndexException();
        }
        return response;
    }

    /**
     * Processes unmark commands to get a response from Bocil.
     *
     * @param split The user's input split by each space.
     * @return Response line of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String processUnmark(String[] split) throws BocilException {
        String response;
        if (split.length < 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        try {
            int num = Integer.parseInt(split[1]);
            response = this.executor.unmarkAsDone(num);
        } catch (NumberFormatException e) {
            throw BocilException.bocilInvalidIndexException();
        }
        return response;
    }

    /**
     * Processes tag commands to get a response from Bocil.
     *
     * @param split The user's input split by each space.
     * @return Response line of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String processTag(String[] split) throws BocilException {
        String response;
        if (split.length < 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        String[] details = split[1].split("\\s+");
        if (details.length < 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        try {
            int num = Integer.parseInt(details[0]);
            String tag = details[1];
            response = this.executor.tagTask(num, tag);
        } catch (NumberFormatException e) {
            throw BocilException.bocilInvalidIndexException();
        }
        return response;
    }

    /**
     * Processes untag commands to get a response from Bocil.
     *
     * @param split The user's input split by each space.
     * @return Response line of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String processUntag(String[] split) throws BocilException {
        String response;
        if (split.length < 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        try {
            int num = Integer.parseInt(split[1]);
            response = this.executor.untagTask(num);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw BocilException.bocilInvalidIndexException();
        }
        return response;
    }

    /**
     * Processes view commands to get a response from Bocil.
     *
     * @param split The user's input split by each space.
     * @return Response line of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String processView(String[] split) throws BocilException {
        String response;
        if (split.length < 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        String tag = split[1];
        if (!isValid(tag)) {
            throw BocilException.bocilInvalidTagFormatException();
        }
        response = this.executor.viewTag(tag);
        return response;
    }

    /**
     * Processes find commands to get a response from Bocil.
     *
     * @param split The user's input split by each space.
     * @return Response line of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String processFind(String[] split) throws BocilException {
        String response;
        if (split.length < 2) {
            throw BocilException.bocilInvalidFormatException();
        }
        String keyword = split[1];
        response = this.executor.findTaskFromList(keyword);
        return response;
    }

    /**
     * Processes list commands to get a response from Bocil.
     *
     * @param split The user's input split by each space.
     * @return Response line of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String processList(String[] split) throws BocilException {
        String response;
        if (split.length > 1) {
            throw BocilException.bocilUnknownCommandException();
        }
        response = this.executor.showList();
        return response;
    }

    /**
     * Processes bye commands to get a response from Bocil.
     *
     * @param split The user's input split by each space.
     * @return Response line of the program.
     * @throws BocilException If the user input is not of the accepted format.
     */
    public String processBye(String[] split) throws BocilException {
        String response;
        if (split.length > 1) {
            throw BocilException.bocilUnknownCommandException();
        }
        response = this.executor.endProgram();
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
