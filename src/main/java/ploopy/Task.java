package ploopy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String name;
    protected boolean done;
    protected String type;
    protected LocalDateTime dateTime;
    protected String dateStringForm;
    protected static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm");
    protected static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy kkmm");

    protected Task(String name, String date) {
        this.name = name;
        this.done = false;

        if (date != null) {
            dateStringForm = date;
            this.dateTime = LocalDateTime.parse(date, inputFormatter);
        } else {
            dateStringForm = "";
        }

    }

    @Override
    public String toString() {
        return String.format("[%s]%s %s", type, getStatus(), name);
    }

    public static Task addTaskFromFile(String type, String name, String date) {
        switch (type) {
            case "T":
                return new ToDo(name);
            case "D":
                return new Deadline(name, date);
            default:
                return new Event(name, date);
        }
    }

    public void markDone() {
        done = true;
    }

    public void unmark() {
        done = false;
    }

    public String getStatus() {
        return done ? "[✓]" : "[ ]";
    }

    protected String getType() {
        return type;
    }

    protected boolean isDone() {
        return done;
    }

    protected String getName() {
        return name;
    }

    protected String getDate() {
        return outputFormatter.format(dateTime);
    }

    protected abstract String stringType();

    protected String getDateForFileWrite() {
        return dateStringForm;
    }
}
