package utils;

import enums.*;

public class CommandDispatcher {
    /**
     * This retrieves the user input
     * 
     * @param input The user input to retrieve from
     * @param type  The type of input we are retrieving
     * @return The argument from the
     */
    public String[] retrieve_arguments(String input, Commands cmd) {
        String[] inputs = input.split("./..."); // remove the /by
        switch (cmd) {
            case GET_COMMAND:
                String[] output = { input.split(" ")[0] };
                return output;
            case ADD_TODO:
                // TODO: Remove the hard-coded substring index
                inputs[0] = inputs[0].substring(5); // user description
                return inputs; // (descrpition)
            case ADD_EVENT:
                return inputs; // (descrpition, date)
            case ADD_DEADLINE:
                return inputs; // (description, date)
            case MARK_DONE:
                String[] indx = { input.split(" ")[1] };
                return indx;
            case DELETE:
                String[] ind = { input.split(" ")[1] };
                return ind;
            default: // No match or Invalid command
                String[] empty = { "" };
                return empty;
        }
    }
}
