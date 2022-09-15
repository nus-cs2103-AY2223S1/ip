package commands;

import common.ChatResponse;
import common.Parser;
import dukeexceptions.DukeException;
import dukeexceptions.MissingDescriptionException;
import dukeexceptions.WrongDatetimeFormatException;
import tasklist.TaskList;
import tasks.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Arrays;

/**
 * Represents a Deadline command to be executed.
 */
public class DeadlineCommand extends Command {
    static final String DEADLINE_DATETIME_FORMAT = "d/M/uuuu HHmm";
    static final String DEADLINE_STORAGE_FORMAT = "MMM dd uuuu, HHmm";
    private final String[] args;

    public DeadlineCommand(String[] args) {
        this.args = args;
    }

    /**
     * Validates the passed arguments before executing the command.
     *
     * @param args Arguments to validate.
     * @throws DukeException Exception to be thrown if validation fails.
     */
    public static void validateArguments(String[] args) throws DukeException {
        assert args.length > 0 : "No arguments entered into DeadlineCommand validateArguments";

        // ERROR HANDLING: Check for missing /by delimiter
        if (!Arrays.asList(args).contains("/by")) {
            throw new DukeException("Please include /by to describe the time range of this Deadline!");
        }


        String description = Parser.splitArrayIntoSubstrings(args, "/by").get(0);
        // ERROR HANDLING: Check for empty Tasks.Deadline description
        if (description.equalsIgnoreCase("")) {
            throw new MissingDescriptionException("deadline");
        }

        // ERROR HANDLING: Check for missing "by" deadline
        String unparsedDatetime = Parser.splitArrayIntoSubstrings(args, "/by").get(1);

        if (!Parser.isValidDatetime(unparsedDatetime, DEADLINE_DATETIME_FORMAT)) {
            throw new WrongDatetimeFormatException(DEADLINE_DATETIME_FORMAT);
        }
    }

    /**
     * Converting String into LocalDateTime object (DateTime Formatting)
     *
     * @return LocalDateTime
     **/
    public static LocalDateTime parseDeadlineDatetime(String s) {
        return LocalDateTime.parse(s,
                DateTimeFormatter.ofPattern(DEADLINE_DATETIME_FORMAT).withResolverStyle(ResolverStyle.STRICT));
    }

    /**
     * Changing DateTime Formatting to Storage Formatting
     *
     * @return LocalDateTime
     **/
    public static LocalDateTime parseDeadlineDatetimeFromStorage(String s) {
        return LocalDateTime.parse(s,
                DateTimeFormatter.ofPattern(DEADLINE_STORAGE_FORMAT).withResolverStyle(ResolverStyle.STRICT));
    }

    @Override
    public String execute(TaskList taskList) {
        String description = Parser.splitArrayIntoSubstrings(this.args, "/by").get(0);

        String unparsedDatetime = Parser.splitArrayIntoSubstrings(this.args, "/by").get(1);
        assert !unparsedDatetime.equals("") : "Parsing error occured in Deadline: datetime";

        LocalDateTime taskDeadline = parseDeadlineDatetime(unparsedDatetime);
        Deadline newDeadline = new Deadline(description, taskDeadline);
        taskList.addTask(newDeadline);
        return ChatResponse.returnChatAddTask(newDeadline, taskList);
    }
}
