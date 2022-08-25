/**
 * Parser
 */
final class Parser {
    private static final String SPACE = " +";
    private static final String SEP = " +/";
    private static final String SAVE_SEP = " <<<< ";

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

    static Task makeTaskFromParsed(ParsedData data) throws DukeException {
        switch (data.command) {
            case "T":
                return Todo.createTodo(data);

            case "D":
                return Deadline.createDeadline(data);

            case "E":
                return Event.createEvent(data);

            default:
                throw new UnknownCommandException();
        }
    }

    static ParsedData parseDataFromLine(String savedLine) throws CorruptedLineException {
        String[] parsedTmp = savedLine.split(SAVE_SEP, 3);
        if (parsedTmp.length != 3) {
            throw new CorruptedLineException(savedLine);
        }
        return ParsedData.makeParsedData(parsedTmp);
    }
}
