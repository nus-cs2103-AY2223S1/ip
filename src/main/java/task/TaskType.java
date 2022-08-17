package task;

import java.util.Arrays;

public enum TaskType {
    TODO ("todo", "T"),
    EVENT ("event", "E"),
    DEADLINE ("deadline", "D");

    private final String name;
    private final String abbreviation;

    TaskType(String s, String abbreviation) {
        this.name = s;
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

}
