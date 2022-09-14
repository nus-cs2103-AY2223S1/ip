package ren.task;

import ren.RenException;
import ren.TimeStamp;

/**
 * Deadline Task represents an action that needs to be done by a certain date or time.
 */
public class Deadline extends Task {
    protected TimeStamp dateTime;

    /**
     * Constructor for a Deadline Task.
     *
     * @param description the description of the Task
     * @param dateTime the deadline of the Task
     */
    public Deadline(String description, String dateTime) throws RenException {
        super(description);
        this.dateTime = TimeStamp.of(dateTime);
    }

    private Deadline(String description, TimeStamp dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns a Deadline Task constructed with data read from File.
     *
     * @param data Data of a Deadline Task.
     * @return Deadline Task or null if data is corrupted.
     */
    public static Deadline readData(String[] data) {
        // Check if data is complete
        assert data.length == 4 : "data[] in readData in Deadline should be of size 4";

        Deadline newDeadline = new Deadline(data[2], TimeStamp.fromFile(data[3]));
        if (data[1].equals("X")) {
            newDeadline.setDone(true);
        }
        return newDeadline;
    }

    /**
     * Returns the Deadline Task information for writing to a File.
     *
     * @return String with Deadline Task information.
     */
    @Override
    public String writeData() {
        String symbol = this.isDone ? "X" : " ";
        return "D|" + symbol + "|" + this.description + "|" + this.dateTime.toString();
    }

    /**
     * Compares this Deadline to another Task by their type of task.
     *
     * @param other The task to compare with.
     * @return -1 if other is an Event, 1 if the other task is a Todo, 0 if the other task is a Deadline.
     */
    @Override
    public int compareType(Task other) {
        if (other instanceof Todo) {
            return 1;
        } else if (other instanceof Deadline) {
            return 0;
        }
        return -1;
    }

    /**
     * Compares this Deadline to another Task by their date.
     *
     * @param other The task to compare with.
     * @return -1 if this task should be sorted first, 1 if the other task should be sorted first, 0 otherwise.
     */
    @Override
    public int compareDate(Task other) {
        if (other instanceof Todo) {
            return -1;
        } else if (other instanceof Deadline) {
            return this.dateTime.compareTo(((Deadline) other).dateTime);
        } else if (other instanceof Event) {
            return this.dateTime.compareTo(((Event) other).start);
        }
        return 0;
    }

    /**
     * Returns the string representation of this Deadline Task.
     *
     * @return String Representation.
     */
    @Override
    public String toString() {
        String symbol = this.isDone ? "X" : " ";
        return "[D][" + symbol + "] " + this.description + "(by:" + this.dateTime + ")\n";
    }
}
