package duke.tasks;

import java.time.LocalDateTime;

/**
 * Represents a class which Event, Deadline and ToDo will inhert from
 * It stores the actual task as a string, and a boolean on weather task is done
 */
public class Task {
    private final String val;
    private final boolean done;

    public Task(String input, String dummy) {
        this.val = input;
        done = false;
    }

    public Task(String input, boolean done, String dummy) {
        this.val = input;
        this.done = done;
    }

    /**
     * @return a new task object, with done boolean switched to true
     */
    public Task markDone() {
        return new Task(this.val, true, "");
    }

    /**
     * @return a new task object, with done boolean switched to false
     */
    public Task markUndone() {
        return new Task(this.val, false, "");
    }

    /**
     * @return getter for the boolean, checking if task is done
     */

    public boolean getDone() {
        return this.done;
    }

    /**
     * @param date the input day
     * @return dummy value, implemented by Deadline and Event
     */
    public boolean sameDay(LocalDateTime date) {return false;}

    /**
     * @return return the String value of this task, what task it is
     */
    public String getVal() {
        return this.val;
    }

    /**
     * @return dummy value, implemented by Storage and Event
     */

    public String getTiming() {
        return "";
    }

    /**
     * @return dummy value, implemented by Storage and Event
     */
    public String toText() {
        return "";
    }
}
