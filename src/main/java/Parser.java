/**
 * Parser
 */
class Parser {
    private static final String SPACE = " +";
    private static final String SEP = " +/";

    Parser() {
    }

    ParsedData parse(String txt) {
        String[] parsedTmp = txt.split(SPACE, 2);

        if (parsedTmp.length == 1 || parsedTmp[1].equals("")) {
            return new ParsedData(txt, parsedTmp[0]);
        }

        String command = parsedTmp[0];
        parsedTmp = parsedTmp[1].split(SEP, 2);

        if (parsedTmp.length == 1 || parsedTmp[1].equals("")) {
            return new ParsedData(txt, command, parsedTmp[0]);
        }

        return new ParsedData(txt, command, parsedTmp[0], parsedTmp[1]);
    }
}
