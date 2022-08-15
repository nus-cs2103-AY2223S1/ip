class ParsedData {
    final String raw;
    final String command;
    final String description;
    final String additionalInfo;

    ParsedData(String raw, String command, String description, String date) {
        this.raw = raw;
        this.command = command;
        this.description = description;
        this.additionalInfo = date;
    }

    ParsedData(String raw) {
        this(raw, "", "", "");
    }

    ParsedData(String raw, String command) {
        this(raw, command, "", "");
    }

    ParsedData(String raw, String command, String description) {
        this(raw, command, description, "");
    }
}