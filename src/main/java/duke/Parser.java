package duke;

public class Parser {

    /**
     * Parses the input string.
     *
     * @param input the input string.
     * @return the parsed string array.
     * @throws DukeException If input is invalid.
     */
    public static String[] parseInput(String input) throws DukeException {
        String[] phrases = input.split(" /.. ", 2); // splits sentence and removes by/at
        String[] words = phrases[0].split(" ", 2);
        if (words.length == 2) {
            words[1] = words[1].trim();
        }

        switch (words[0]) {
        case "todo":
            return parseTodo(words);
        case "find":
            return parseFind(words);
        case "deadline":
        case "event":
            return parseEvent(words, phrases);
        case "mark":
        case "unmark":
        case "delete":
            return parseWithIndex(words);
        default:
            return parseWord(words);
        }
    }

    private static String[] parseTodo(String[] words) throws DukeException {
        if (words.length != 2 || words[1].equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return words;
    }

    private static String[] parseFind(String[] words) throws DukeException {
        if (words.length != 2) {
            throw new DukeException("Please add in item to find.");
        }
        return words;
    }

    private static String[] parseEvent(String[] words, String[] phrases) throws DukeException {
        if (words.length != 2 || words[1].equals("")) {
            throw new DukeException("The description of a task cannot be empty.");
        }
        if (phrases.length != 2) {
            throw new DukeException("Please add in timing information.");
        }
        String[] output = new String[3];
        try {
            output[0] = words[0]; // type of task
            output[1] = words[1]; // task detail
            output[2] = phrases[1]; // task timing information
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid Input");
        }
        return output;
    }

    private static String[] parseWord(String[] words) throws DukeException {
        if (words.length != 1) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return words;
    }

    private static String[] parseWithIndex(String[] words) throws DukeException {
        if (words.length != 2 || words[1].equals("")) {
            throw new DukeException("Please put in index");
        }
        return words;
    }
}

