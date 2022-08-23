package utility;
import task.Task;

/**
 * Parses all lines to be stored or retrieved from file.
 */
public class StorageParser {

    public static String storableDescription(Task t) {
        return t.toString() + "\n";
    }
}
