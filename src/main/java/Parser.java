
// deals with making sense of the user command
public class Parser {

    public Pair<Duke.Command, String> parse(String s) throws DukeException, DukeTaskException{

        boolean isMultipleWords = false;
        String firstWord = "";
        String restWord = "";

        int indexOfSpace = s.indexOf(' ');
        isMultipleWords = indexOfSpace > -1;
        firstWord = s;
        restWord = "";
        if (isMultipleWords) {
            firstWord = s.substring(0, indexOfSpace);
            restWord = s.substring(indexOfSpace).trim();
        }

        switch(firstWord) {
            case "bye":
                return new Pair(Duke.Command.BYE, "");
                //Fallthrough
            case "list":
                return new Pair(Duke.Command.LIST, "");
                //Fallthrough
            case "mark":
                if (!isMultipleWords) {
                    throw new DukeException("Index of task to mark required");
                }
                return new Pair(Duke.Command.MARK, restWord);
                //Fallthrough
            case "unmark":
                if (!isMultipleWords) {
                    throw new DukeException("Index of task to mark required");
                }
                return new Pair(Duke.Command.UNMARK, restWord);
                //Fallthrough
            case "delete":
                if (!isMultipleWords) {
                    throw new DukeException("Index of task to delete required");
                }
                return new Pair(Duke.Command.DELETE, restWord);
                //Fallthrough
            case "todo":
                return new Pair(Duke.Command.TODO, restWord);
                //Fallthrough
            case "deadline":
                if (!restWord.contains("/by")) {
                    throw new DukeTaskException("Deadlines require command '/by' to signify time");
                }
                return new Pair(Duke.Command.DEADLINE, restWord);
                //Fallthrough
            case "event":
                if (!restWord.contains("/at")) {
                    throw new DukeTaskException("Events require command '/at' to signify time");
                }
                return new Pair(Duke.Command.EVENT, restWord);
                //Fallthrough
            default:
                throw new DukeException("?? Unrecognised command");
                //Fallthrough
            }
        }
}
