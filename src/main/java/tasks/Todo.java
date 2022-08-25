package tasks;

import util.ParsedData;

public class Todo extends Task {

    public Todo(ParsedData parsedData) {
        super(parsedData);
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String getTypeLetter() {
        return "T";
    }

    @Override
    public String getDuring() {
        return "";
    }

    @Override
    public String getTimeText() {
        return "";
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon() + this.description;
    }
}
