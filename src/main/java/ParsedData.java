class ParsedData {
    final String command;
    final String description;
    final String additionalInfo;

    private static final String SEP = " <<<< ";

    ParsedData(String command, String description, String date) {
        this.command = command;
        this.description = description;
        this.additionalInfo = date;
    }

    ParsedData() {
        this("", "", "");
    }

    ParsedData(String command) {
        this(command, "", "");
    }

    ParsedData(String command, String description) {
        this(command, description, "");
    }

    public String getSavedString() {
        return String.format("%s%s%s%s%s", command, SEP, description, SEP, additionalInfo);
    }
}