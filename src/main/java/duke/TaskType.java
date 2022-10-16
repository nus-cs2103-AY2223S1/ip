package duke;

public enum TaskType {
    TODO("t ", "task"),
    DEADLINE("d ", "deadline"),
    EVENT("e ", "event");

    public final String command;
    public final String name;

    TaskType(String command, String name) {
        this.command = command;
        this.name = name;
    }
}