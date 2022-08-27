package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    protected enum Type {TODO, EVENT, DEADLINE}

    protected Type type;
    protected LocalDate date;

    /**
     * Instantiates a new Task object
     */
    public Task(String description, String type, LocalDate date) {
        this.description = description;
        this.isDone = false;
        this.date = date;
        if ("todo".equals(type)) {
            this.type = Type.TODO;
        } else if ("event".equals(type)) {
            this.type = Type.EVENT;
        } else if ("deadline".equals(type)) {
            this.type = Type.DEADLINE;
        } else {
            System.out.println("Invalid choice, please choose either todo, event or deadline");
        }
    }

    /**
     * Instantiates a new Task object
     */
    public Task(String data) {
        String[] temp = data.split("\\|"); //0:type 1:description 2:status 3:date (optional)
        this.description = temp[1];
        this.isDone = "1".equals(temp[2]);

        if (temp.length > 3) { //contains date
            this.date = LocalDate.parse(temp[3].strip());
        } else {
            this.date = null;
        }

        if ("todo".equals(temp[0])) {
            this.type = Type.TODO;
        } else if ("event".equals(temp[0])) {
            this.type = Type.EVENT;
        } else if ("deadline".equals(temp[0])) {
            this.type = Type.DEADLINE;
        } else {
            System.out.println("Invalid choice, please choose either todo, event or deadline");
        }
    }

    /**
     * Sets the status icon of this Task
     */
    public void setStatusIcon(boolean b) {
        this.isDone = b;
    }

    /**
     * Returns the status icon of this Task
     *
     * @return the status icon of this Task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the short form of the type of this Task
     *
     * @return the short form of the type of this Task
     */
    public String getType() {
        switch (this.type) {
        case TODO:
            return "T";
        case EVENT:
            return "E";
        case DEADLINE:
            return "D";
        }
        ;
        return "";
    }

    /**
     * Returns the long form of the type of this Task
     *
     * @return the long form of the type of this Task
     */
    public String getTypeLong() {
        switch (this.type) {
        case TODO:
            return "todo";
        case EVENT:
            return "event";
        case DEADLINE:
            return "deadline";
        }
        ;
        return "";
    }

    /**
     * Returns the status of this Task
     *
     * @return the status of this Task
     */
    public String getStatus() {
        if (this.isDone) {
            return "1";
        }
        return "0";
    }

    /**
     * Returns the String object representing this Task
     *
     * @return the String object representing this Task
     */
    public String toString() {
        switch (this.type) {
        case TODO:
            return "[" + this.getType() + "]" + "[" + this.getStatusIcon() + "] " + this.description;
        case DEADLINE:
            return "[" + this.getType() + "]" + "[" + this.getStatusIcon() + "] " + this.description
                    + "(by " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        case EVENT:
            return "[" + this.getType() + "]" + "[" + this.getStatusIcon() + "] " + this.description
                    + "(at " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return "";
    }

    /**
     * Returns the String object to be stored as data representing this Task
     *
     * @return the String object to be stored as data representing this Task
     */
    public String getData() {
        switch (this.type) {
        case TODO:
            return getTypeLong() + "|" + this.description + "|" + this.getStatus();
        default:
            return getTypeLong() + "|" + this.description + "|" + this.getStatus() + "|" + this.date;
        }

    }
}
