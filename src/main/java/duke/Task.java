package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected enum Type {TODO, EVENT, DEADLINE}
    protected Type type;
    protected LocalDate date;

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

    public void setStatusIcon(boolean b) {
        this.isDone = b;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getType() {
        switch (this.type) {
            case TODO:
                return "T";
            case EVENT:
                return "E";
            case DEADLINE:
                return "D";
        };
        return "";
    }

    public String getTypeLong() {
        switch (this.type) {
            case TODO:
                return "todo";
            case EVENT:
                return "event";
            case DEADLINE:
                return "deadline";
        };
        return "";
    }

    public String getStatus() {
        if (this.isDone) {
            return "1";
        }
        return "0";
    }

    public String toString() {
        switch (this.type) {
            case TODO:
                return "["+ this.getType() + "]" + "[" + this.getStatusIcon() + "] " + this.description;
            case DEADLINE:
                return "["+ this.getType() + "]" + "[" + this.getStatusIcon() + "] " + this.description
                        + "(by " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
            case EVENT:
                return "["+ this.getType() + "]" + "[" + this.getStatusIcon() + "] " + this.description
                        + "(at " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return "";
    }

    public String getData() {
        switch (this.type) {
            case TODO:
                return getTypeLong() + "|" + this.description + "|" + this.getStatus();
            default:
                return getTypeLong() + "|" + this.description + "|" + this.getStatus() + "|" + this.date;
        }

    }
}
