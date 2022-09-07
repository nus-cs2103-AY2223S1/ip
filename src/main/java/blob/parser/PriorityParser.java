package blob.parser;

import blob.exception.InvalidPriorityException;
import blob.tasks.Priority;

/**
 * The PriorityParser deals with parsing priorities of task
 */
public class PriorityParser {

    /**
     * Parses the given priority and returns a Priority
     *
     * @param inputPriority The given priority.
     * @return A Priority based on the parsed input
     */
    public Priority parse(String inputPriority) throws InvalidPriorityException {
        String cleanedInput = inputPriority.trim().toUpperCase();

        switch(cleanedInput) {
        case "//H":
            return Priority.HIGH;

        case "//M":
            return Priority.MED;

        case "//L":
            return Priority.LOW;

        default:
            throw new InvalidPriorityException();
        }

    }
}
