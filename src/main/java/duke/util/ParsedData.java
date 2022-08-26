package duke.util;

/**
 * Class to represent parsed data.
 * <code>ParsedData</code> can be used to store 3 seperate instance of
 * information to be used by other classes.
 */
public class ParsedData {
    public final String command;
    public final String description;
    public final String additionalInfo;

    private static final String SEP = " <<<< ";

    public ParsedData(String command, String description, String date) {
        this.command = command;
        this.description = description;
        this.additionalInfo = date;
    }

    public ParsedData() {
        this("", "", "");
    }

    public ParsedData(String command) {
        this(command, "", "");
    }

    public ParsedData(String command, String description) {
        this(command, description, "");
    }

    /**
     * Returns saved data as a string to be stored.
     * 
     * @return String
     */
    public String getSavedString() {
        return String.format("%s%s%s%s%s", command, SEP, description, SEP, additionalInfo);
    }
}