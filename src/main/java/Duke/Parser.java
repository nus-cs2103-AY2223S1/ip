package Duke;

public class Parser {

    /**
     * Parses the input string.
     * @param input the input string.
     * @return the parsed string array.
     * @throws DukeException If input is invalid.
     */
    public static String[] parseInput(String input) throws DukeException {
        String[] phrases = input.split(" /.. ", 2); // splits sentence and removes by/at
        String[] words = phrases[0].split(" ", 2);

        if (words[0].equals("todo")) {
            if (words.length != 2) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else {
                return words;
            }
        } else if (words[0].equals("find")) {
            if (words.length != 2) {
                throw new DukeException("Please add in item to find.");
            } else {
                return words;
            }
        } else if (words[0].equals("deadline") || words[0].equals("event")) {
            if (phrases.length != 2) {
                throw new DukeException("Please add in timing information.");
            } else {
                String[] output = new String[3];
                output[0] = words[0]; // type of task
                output[1] = words[1]; // task detail
                output[2] = phrases[1]; // task timing information
                return output;
            }
        } else if (words[0].equals("bye") || words[0].equals("list")) {
            if (words.length != 1) {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            } else {
                return words;
            }
        } else if (words[0].equals("mark") || words[0].equals("unmark") || words[0].equals("delete")) {
            if (words.length != 2) {
                throw new DukeException("Please put in index");
            } else {
                return words;
            }
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
