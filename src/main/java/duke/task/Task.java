package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.command.CommandType;
import duke.dukeexception.DukeException;

/**
 * Represents a task.
 */
public class Task {
    private String description;
    private boolean isDone;
    private LocalDate day;
    private String fullDescription;
    /**
     * Class constructor.
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return the status of this task
     * @return " " if the task is not done, "X" if the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Mark this task as Done without reply
     */
    public void taskDone() {
        this.isDone = true;
    }
    public void correctDescrition(String rightDescription) {
        this.description = rightDescription;
    }
    /**
     * Undone this task and print reply
     */
    public void taskUndone() {
        this.isDone = false;
    }

    /**
     * Print the representation of this task containing status and description
     * @return A string of right format describe the task.
     */
    public String printTask() {
        return ("[" + this.getStatusIcon() + "]" + " " + this.description);
    }

    /**
     * Create a certain kind of task.
     * @param c User command type.
     * @param s User input of description of task.
     * @return The created task.
     */
    public static Task createATask(String s, CommandType c) throws DukeException {
        switch (c) {
        case TODO:
            return new ToDo(s);
        case DEADLINE:
            return new DeadLine(s);
        case EVENT:
            return new Event(s);
        default:
            throw new DukeException("Sorry, something went wrong when creating task.");
        }
    }
    /**
     * Mark this task as Done according to status.
     */
    public void markStatus(String status) {
        if (status.equals("X")) {
            this.taskDone();
        }
    }

    public String getDescription() {
        return this.description;
    }

    public void getFullDescription(String s) {
        this.fullDescription = s;
    }
    /**
     * Prints time in MMM dd yyyy format.
     */
    public String showTime() throws DukeException {
        try {
            String[] s = fullDescription.split(" ");
            String[] s2 = s[s.length - 2].split("/");
            String time = s2[2];
            if (s2[1].length() < 2) {
                time += "-0" + s2[1];
            } else {
                time += "-" + s2[1];
            }
            if (s2[0].length() < 2) {
                time += "-0" + s2[0];
            } else {
                time += "-" + s2[0];
            }
            this.day = LocalDate.parse(time);
            return this.day.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (Exception e) {
            throw new DukeException("");
        }

    }
    /**
     * Return true if this task is on target day.
     * @param d The day to be found.
     */
    public boolean isOnSpecificDay(LocalDate d) {
        if (this.day == null) {
            return false;
        }
        if (this.day.equals(d)) {
            return true;
        } else {
            return false;
        }
    }
}
