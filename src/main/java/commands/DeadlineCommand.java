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

public class DeadlineCommand extends Command {
    static final String DEADLINE_DATETIME_FORMAT = "d/MM/uuuu HHmm";
    static final String DEADLINE_STORAGE_FORMAT = "MMM dd uuuu, HHmm";
    private final String[] args;

    public DeadlineCommand(String[] args) {
        this.args = args;
    }

    public static void validateArguments(String[] args) throws DukeException {
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

    public static LocalDateTime parseDeadlineDatetime(String s) {
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(DEADLINE_DATETIME_FORMAT).withResolverStyle(ResolverStyle.STRICT));
    }

    public static LocalDateTime parseDeadlineDatetimeFromStorage(String s) {
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(DEADLINE_STORAGE_FORMAT).withResolverStyle(ResolverStyle.STRICT));
    }

    @Override
    public String execute(TaskList taskList) {
        String description = Parser.splitArrayIntoSubstrings(this.args, "/by").get(0);

        String unparsedDatetime = Parser.splitArrayIntoSubstrings(this.args, "/by").get(1);
        LocalDateTime taskDeadline = parseDeadlineDatetime(unparsedDatetime);
        Deadline newDeadline = new Deadline(description, taskDeadline);
        taskList.addTask(newDeadline);
        return ChatResponse.returnChatAddTask(newDeadline, taskList);
    }
}
