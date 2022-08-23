package duke.task;

import duke.Parser;
import duke.exception.DukeRuntimeException;
import duke.exception.ReadAttributeException;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Task {
    private String name;
    private boolean isDone;

    protected Task(String name) {
        setName(name);
        setIsDone(false);
    }

    public static Todo todo(String msg) {
        return new Todo(msg);
    }

    public static Event event(String msg, LocalDateTime time) {
        return new Event(msg, time);
    }

    public static Deadline deadline(String msg, LocalDateTime time) {
        return new Deadline(msg, time);
    }

    protected static int convertBoolToInt(boolean bool) {
        return bool ? 1 : 0;
    }

    protected static boolean convertIntToBool(int i) {
        if (i == 1) {
            return true;
        } else if (i == 0) {
            return false;
        } else {
            throw new DukeRuntimeException(i + " is not defined when converting int to bool.");
        }
    }

    public static Task parseTask(String formattedString) throws ReadAttributeException {
        ArrayList<String> attributes = Parser.separateAttributes(formattedString);
        switch (attributes.get(0)) {
        case Todo.SYMBOL:
            return Todo.parseTodo(formattedString);
        case Event.SYMBOL:
            return Event.parseEvent(formattedString);
        case Deadline.SYMBOL:
            return Deadline.parseDeadline(formattedString);
        default:
            throw new ReadAttributeException(
                    "Task", formattedString, "Task Symbol: [" + attributes.get(0) + "] is invalid.");
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    private void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract String toFormattedString();

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + this.getName();
    }

}
