package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/*
Possible types of tasks to be created
*/
enum TaskType {
    TODO, DEADLINE, EVENT;
}

/*
Task object that gets created by the user
*/
public class Task {
    private boolean isDone;
    private String taskName;
    private TaskType taskType;
    private LocalDate date;

    public Task (String taskName, String taskType, String timing, boolean done) {
        this.isDone = done;
        this.taskName = taskName;
        this.date = null;
        switch (taskType) {
        case "T":
            this.taskType = TaskType.TODO;
            return;
        case "D":
            this.taskType = TaskType.DEADLINE;
            this.date = LocalDate.parse(timing);
            return;
        case "E":
            this.taskType = TaskType.EVENT;
            this.date = LocalDate.parse(timing);
        }
    }

    public Task (String taskName, String taskType, String timing) {
        this.isDone = false;
        this.taskName = taskName;
        this.date = null;
        switch (taskType.toUpperCase(Locale.ROOT)) {
            case "TODO":
                this.taskType = TaskType.TODO;
                return;
            case "DEADLINE":
                this.taskType = TaskType.DEADLINE;
                this.date = LocalDate.parse(timing);
                return;
            case "EVENT":
                this.taskType = TaskType.EVENT;
                this.date = LocalDate.parse(timing);
        }
    }


    public String getTask() {
        return this.taskName;
    }

    public String isDoneString() {
        if (isDone) {
            return "[X]";
        }
        return "[ ]";
    }

    private String getTypeLetter() {
        switch (this.taskType) {
            case TODO:
                return "T";
            case EVENT:
                return "E";
            case DEADLINE:
                return "D";
        }
        return " ";
    }

    @Override
    public String toString() {
        if (this.date != null) {
            return "[" + getTypeLetter() + "]" + isDoneString() + " " + this.taskName + " " + this.date;
        }
        return "[" + getTypeLetter() + "]" + isDoneString() + " " + this.taskName;
    }

    public String toTxt() {
        if (isDone) {
            return taskType.toString().charAt(0) + " | 1 | " + this.taskName + " | " +
                    this.date + "\n";
        }
        return this.taskType.toString().charAt(0) + " | 0 | " + this.taskName + " | " +
                this.date + "\n";
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public boolean onDate(String date) {
        if (this.date == null) {
            return false;
        }
        return this.date.toString().equals(date);
    }

    public void updateDate(String date) throws DukeException {
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("please input a valid date in YYYY-MM-DD");
        }
    }

}
