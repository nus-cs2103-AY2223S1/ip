package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

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
    private boolean done;
    private String taskName;
    private TaskType taskType;
    private LocalDate date;
    private LocalTime time;

    public Task (String taskName, String taskType, boolean done) {
        this.done = done;
        this.time = null;
        if (taskType.equals("TODO")) {
            this.taskType = TaskType.TODO;
            this.taskName = taskName;
            this.date = null;
            this.time = null;
        } else if (taskType.equals("DEADLINE") || taskType.equals("EVENT")) {
            if (taskType.equals("EVENT")) {
                this.taskType = TaskType.EVENT;
            } else {
                this.taskType = TaskType.DEADLINE;
            }
            this.taskName = taskName.substring(0, taskName.indexOf("/")).trim();
            try {
                this.date = LocalDate.parse(taskName.substring(taskName.indexOf("/") + 4, taskName.indexOf("/") + 14));
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage().substring(e.getMessage().indexOf(": ") + 2));
            }
            // for localtime 
            /* if (taskName.substring(taskName.indexOf("/") + 4).trim().length() != 0) {
                String hhmm = taskName.substring(taskName.indexOf("/" + 16)).trim();
                int hour = Integer.parseInt(hhmm.substring(0, 1));
                int min = Integer.parseInt(hhmm.substring(2, 3));
                try {
                    this.time = LocalTime.of(hour, min);
                } catch (DateTimeParseException e) {
                    System.out.println(e.getMessage().substring(e.getMessage().indexOf(": ") + 2));
                }
            } */
        }
    }

    public Task (String taskName, String taskType, String timing, boolean done) {
        this.done = done;
        this.taskName = taskName;
        this.time = null;
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


    public String getTask() {
        return this.taskName;
    }

    public String isDoneString() {
        if (done) {
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
        return "[" + getTypeLetter() + "]" + isDoneString() + " " + this.taskName + " " + this.date;
    }

    public String toTxt() {
        /* if (taskType == duke.TaskType.TODO) {
            if (done) {
                return taskType.toString().charAt(0) + " | 1 | " + this.taskName + "\n";
            }
            return taskType.toString().charAt(0) + " | 0 | " + this.taskName + "\n";
        } */
        if (done) {
            return taskType.toString().charAt(0) + " | 1 | " + this.taskName + " | " +
                    this.date + timeToString(this.time) + "\n";
        }
        return this.taskType.toString().charAt(0) + " | 0 | " + this.taskName + " | " +
                this.date + timeToString(this.time) + "\n";
    }

    public void markDone() {
        done = true;
    }

    public void unmarkDone() {
        done = false;
    }

    public boolean onDate(String date) {
        if (this.date == null) {
            return false;
        }
        return this.date.toString().equals(date);
    }

    private String timeToString(LocalTime lt) {
        if (lt != null) {
            return " " + lt;
        }
        return "";
    }

}
