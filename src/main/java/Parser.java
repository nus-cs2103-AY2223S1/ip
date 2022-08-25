import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser
 */
final class Parser {
    private static final String SPACE = " +";
    private static final String SEP = " +/";
    private static final Pattern SAVE_PATTERN = Pattern.compile("^([TDE])([cx]) <<<< (.*) <<<< (.*)");

    Parser() {
    }

    static ParsedData parse(String txt) {
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
    
    static Task parseDataFromLine(String savedLine) throws CorruptedLineException {
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
            ret.completed = true;
        } else if (!result.group(2).equals("x")) {
            throw new CorruptedLineException();
        }

        return ret;

    }
}
