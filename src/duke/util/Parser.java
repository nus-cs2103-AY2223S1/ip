package duke.util;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.CorruptedLineException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Parser
 */
public final class Parser {
    private static final String SPACE = " +";
    private static final String SEP = " +/";
    private static final Pattern SAVE_PATTERN = Pattern.compile("^([TDE])([cx]) <<<< (.*) <<<< (.*)");

    static enum DateFormatEnum {
        T1("MMM d yyyy"),
        T2("dd/MM/yyyy"),
        T3("dd-MM-yyyy");

        final DateTimeFormatter dtf;

        DateFormatEnum(String signature) {
            dtf = DateTimeFormatter.ofPattern(signature);
        }
    }

    static enum DateTimeFormatEnum {
        T1("MMM d yyyy HH:mm"),
        T2("dd/MM/yyyy HH:mm"),
        T3("dd-MM-yyyy HH:mm");

        final DateTimeFormatter dtf;

        DateTimeFormatEnum(String signature) {
            dtf = DateTimeFormatter.ofPattern(signature);
        }
    }

    Parser() {
    }

    public static ParsedData parse(String txt) {
        String[] parsedTmp = txt.split(SPACE, 2);

        if (parsedTmp.length == 1 || parsedTmp[1].equals("")) {
            return new ParsedData(parsedTmp[0]);
        }

        String command = parsedTmp[0];
        parsedTmp = parsedTmp[1].split(SEP, 2);

        if (parsedTmp.length == 1 || parsedTmp[1].equals("")) {
            return new ParsedData(command, parsedTmp[0]);
        }

        return new ParsedData(command, parsedTmp[0], parsedTmp[1]);
    }

    public static Task parseDataFromLine(String savedLine) throws CorruptedLineException {
        Matcher result = SAVE_PATTERN.matcher(savedLine);
        if (!result.find()) {
            throw new CorruptedLineException();
        }

        Task ret;
        switch (result.group(1)) {
            case "T":
                ret = Todo.createTodo(result.group(3));
                break;

            case "D":
                ret = Deadline.createDeadline(result.group(3), result.group(4));
                break;
            case "E":
                ret = Event.createEvent(result.group(3), result.group(4));
                break;
            default:
                throw new CorruptedLineException();
        }

        if (result.group(2).equals("c")) {
            ret.mark();
        } else if (!result.group(2).equals("x")) {
            throw new CorruptedLineException();
        }

        return ret;

    }

    public static Optional<LocalDateTime> strToDateTime(String str) {
        for (DateTimeFormatEnum signature : DateTimeFormatEnum.values()) {
            try {
                return Optional.of(LocalDateTime.parse(str, signature.dtf));
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        for (DateFormatEnum signature : DateFormatEnum.values()) {
            try {
                return Optional.of(LocalDate.parse(str, signature.dtf).atStartOfDay());
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        return Optional.empty();
    }

}
