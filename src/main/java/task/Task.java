package task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.InvalidDateException;

public class Task {
    protected DateTimeFormatter inputDateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    protected DateTimeFormatter outputDateFormatter = DateTimeFormatter.ofPattern("E, d MMM yyyy");
    protected String type;
    protected String description;
    protected LocalDate dateTime;
    protected boolean isDone;
    protected boolean isEmpty;

    private Task() {
        this.isEmpty = true;
    }

    Task (String type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
        this.isEmpty = false;
    }

    Task (String type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
        this.isEmpty = false;
    }

    Task (String type, String description, String dateTime) throws InvalidDateException {
        this.type = type;
        this.description = description;
        this.dateTime = convert(dateTime);
        this.isDone = false;
        this.isEmpty = false;
    }

    Task (String type, String description, String dateTime, boolean isDone) throws InvalidDateException{
        this.type = type;
        this.description = description;
        this.dateTime = convert(dateTime);
        this.isDone = isDone;
        this.isEmpty = false;
    }

    public static Task empty() {
        return new Task();
    }

    public boolean isEmpty() {
        return this.isEmpty;
    }

    public String getDescription() {
        return this.description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String log() {
        int binIsDone;
        if (this.isDone) {
            binIsDone = 1;
        } else {
            binIsDone = 0;
        }
        switch(type) {
        case "todo":
            return String.format("%d,todo %s\n", binIsDone, this.description);
        case "deadline":
            return String.format("%d,deadline %s/by%s\n", binIsDone, this.description, this.dateTime.format(inputDateFormatter));
        case "event":
            return String.format("%d,event %s/at%s\n", binIsDone, this.description, this.dateTime.format(inputDateFormatter));
        default:
            return "N";
        }
    }

    public LocalDate convert(String date) throws InvalidDateException { //assumes format is in d/M/yyyy
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate taskDate = LocalDate.parse(date, inputDateFormatter);
            if (taskDate.isBefore(currentDate)) {
                throw new InvalidDateException("Date should be a future date, not one in the past");
            }
            return taskDate;
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Date is formatted wrongly");
        }
    }

    @Override
    public String toString() {
        char mark;
        if (this.isDone) {
            mark = 'X';
        } else {
            mark = ' ';
        }
        return ("[" + mark + "] " + this.getDescription());
    }
}