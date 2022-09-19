package duke;

/**
 * Parser deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses a given input to identify the command given.
     *
     * @param s String of command to be carried out.
     * @return Pair containing the parsed command and details.
     * @throws DukeException  If something went wrong when constructing a task.
     */
    public Pair<Duke.Command, String> parse(String s) throws DukeException {

        boolean isMultipleWords;
        String firstWord = s;
        String restWord = "";

        int indexOfSpace = s.indexOf(' ');
        isMultipleWords = indexOfSpace > -1;
        if (isMultipleWords) {
            firstWord = s.substring(0, indexOfSpace);
            restWord = s.substring(indexOfSpace).trim();
        }

        switch(firstWord) {
            case "bye":
                return new Pair<>(Duke.Command.BYE, "");
                //Fallthrough
            case "list":
                return new Pair<>(Duke.Command.LIST, "");
                //Fallthrough
            case "mark":
                if (!isMultipleWords) {
                    throw new DukeException("Index of task to mark required");
                }
                return new Pair<>(Duke.Command.MARK, restWord);
                //Fallthrough
            case "unmark":
                if (!isMultipleWords) {
                    throw new DukeException("Index of task to mark required");
                }
                return new Pair<>(Duke.Command.UNMARK, restWord);
                //Fallthrough
            case "delete":
                if (!isMultipleWords) {
                    throw new DukeException("Index of task to delete required");
                }
                return new Pair<>(Duke.Command.DELETE, restWord);
                //Fallthrough
            case "todo":
                return new Pair<>(Duke.Command.TODO, restWord);
                //Fallthrough
            case "deadline":
                if (!restWord.contains("/by")) {
                    throw new DukeTaskException("Deadlines require command '/by' to signify time");
                }
                return new Pair<>(Duke.Command.DEADLINE, restWord);
                //Fallthrough
            case "event":
                if (!restWord.contains("/at")) {
                    throw new DukeTaskException("Events require command '/at' to signify time");
                }
                return new Pair<>(Duke.Command.EVENT, restWord);
                //Fallthrough
            case "fdt":
                if (!restWord.contains("/for")) {
                    throw new DukeTaskException("Events require command '/for' to signify duration");
                }
                return new Pair<>(Duke.Command.FIXEDDURATIONTASK, restWord);
            //Fallthrough
            case "find":
                if (restWord.equals("")) {
                    throw new DukeTaskException("Keyword to find cannot be empty");
                }
                return new Pair<>(Duke.Command.FIND, restWord);
            case "hello":
                return new Pair<>(Duke.Command.HELLO, restWord);
            default:
                throw new DukeException("?? Unrecognised command");
                //Fallthrough
            }
        }
}
