package duke;

/**
 * A parser that makes sense of commands from user input.
 */
public class Parser {
    private Command command;
    private String args;

    /**
     * Parses the given String and stores any commands found.
     * Parsing is successful if a valid command has been identified and stored from the String.
     *
     * @param s The String to be parsed.
     * @return True if parsing was successful.
     */
    public boolean parse(String s) {
        String[] words = s.split(" ", 2);
        args = words.length == 2 ? words[1] : "";
        try {
            command = Command.valueOf(words[0].toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            command = null;
            return false;
        }
    }

    /**
     * Runs the command stored in the Parser with the given Duke bot.
     *
     * @param duke Duke bot running the command.
     */
    public void runCommand(Duke duke) {
        if (command != null) {
            command.run(args, duke);
        }
    }
}
