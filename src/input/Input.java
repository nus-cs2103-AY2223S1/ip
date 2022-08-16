package input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Input is an intermediate representation of input given by the user through the CLI according to certain rules.
 * Provides information about command name, argument name and parameters (if any), and the original input string.
 *  */

/*
* Rules:
* Delimiter is " "
* Command name is the first token in the string
* All arguments start with / e.g /name. and are space separated. Parameters should not contain /, it will be treated as arg.
* Argument parameters come after an argument and end before the next argument or till the end of the string.
*
* */
public class Input {
    private static final String DELIMITER = " ";
    private static final String ARG_START = "/";

    protected String commandName;
    protected String inputString;
    protected Map<String,String> parameters; // store arguments without arg_start
    protected String[] tokens;

    private Input(String input) throws IllegalArgumentException {
        // remove trailing space
        this.inputString = input.trim();

        if (this.inputString.length() == 0) {
            throw new IllegalArgumentException("Input should not be empty!");
        }

        String[] tokens = this.inputString.split(DELIMITER);
        this.tokens = tokens;

        if (tokens[0].startsWith(ARG_START)) {
            throw new IllegalArgumentException(String.format("Commands do not start with %s :)", ARG_START));
        }

        this.commandName = tokens[0];
        this.parameters = new HashMap<>();

        // currArg string and buffer for the strings that are in its param
        String currentArg = null;
        List<String> currentBuffer = new ArrayList<>();

        // argument->param (if any)
        // just a '/' as token: ignore (it can be part of a string)
        for (int i = 1; i < tokens.length; i++) {
            String token = tokens[i];

            // not a valid arg and no current arg: move to next token
            // else: add token to buffer for curr arg
            if (token.equals(ARG_START) || !token.startsWith(ARG_START)) {
                if (currentArg == null) continue;
                currentBuffer.add(token);
            } else {
                // Add previous arg with value, reset buffer
                if (currentArg != null) {
                    // don't include argstart so it is indep. of how the args are received
                    String strippedArg = currentArg.substring(currentArg.lastIndexOf(ARG_START) + ARG_START.length());
                    parameters.put(strippedArg, String.join(DELIMITER, currentBuffer));
                    currentBuffer = new ArrayList<>();
                }

                // set currentArg to new token
                currentArg = token;
            }
        }

        // the last valid arg
        if (currentArg != null) {
            String strippedArg = currentArg.substring(currentArg.lastIndexOf(ARG_START) + ARG_START.length());
            parameters.put(strippedArg, String.join(DELIMITER, currentBuffer));
        }
    }

    /**
     * @return Number of tokens in the input string
     */
    public int getNumberOfTokens() {
        return tokens.length;
    }

    /**
     * @param index Integer for token index
     * @return String token at index provided
     * @throws IllegalArgumentException - if index is not a valid 0-index into tokens
     */
    public String getTokenAtIndex(int index) throws IllegalArgumentException {
        if (index < 0 || index > tokens.length - 1) {
            throw new IllegalArgumentException("Invalid token index");
        }

        return tokens[index];
    }

    /** Factory method to return new instance of Input
     * @param input String input to process
     * @return Input instance representing intermediate representation of the input string
     * @throws IllegalArgumentException
     */
    public static Input newInput(String input) throws IllegalArgumentException {
        return new Input(input);
    }

    @Override
    public String toString() {
        String s = "";
        s += String.format("Command: %s%n", commandName);
        s += String.format("Params: %s%n", parameters.toString());
        return s;
    }
}
