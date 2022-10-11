package rattus.chatbot.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rattus.chatbot.data.exception.InvalidInputException;
import rattus.chatbot.data.exception.InvalidStorageFileException;
import rattus.chatbot.data.task.Deadline;
import rattus.chatbot.data.task.Event;
import rattus.chatbot.data.task.Task;
import rattus.chatbot.data.task.ToDo;

/**
 * A class to handle decoding of tasks stored on files.
 */
public class TaskDecoder {
    /**
     * A Java regex pattern for encoding tasks.
     */
    private static final Pattern BASIC_TASK_ENCODE_FORMAT = Pattern.compile(
            "(?<type>\\w)`(?<status>\\d)`(?<description>.*)`(?<dateTime>.*)"
    );

    /**
     * Returns an instance of Task after decoding the encodedTask argument.
     *
     * @param encodedTask An encoded string representation of a task.
     * @return The task that was encoded.
     * @throws InvalidStorageFileException If the file format is invalid.
     */
    public static Task decode(String encodedTask) throws InvalidStorageFileException {
        Matcher matcher = BASIC_TASK_ENCODE_FORMAT.matcher(encodedTask);
        if (!matcher.matches()) {
            throw new InvalidStorageFileException();
        }
        String type = matcher.group("type").strip();
        boolean status = matcher.group("status").strip().equals("1");
        String description = matcher.group("description").strip();
        String dateTime = matcher.group("dateTime").strip();
        try {
            switch (type) {
            case "T":
                return new ToDo(description, status);
            case "D":
                return new Deadline(description, Parser.parseDateTime(dateTime), status);
            case "E":
                return new Event(description, Parser.parseDateTime(dateTime), status);
            default:
                throw new InvalidStorageFileException();
            }
        } catch (InvalidInputException e) {
            throw new InvalidStorageFileException();
        }
    }
}
