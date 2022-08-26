package duke.util;

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

    public String getSavedString() {
        return String.format("%s%s%s%s%s", command, SEP, description, SEP, additionalInfo);
    }
}