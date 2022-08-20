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
    public Deadline(String description, String dateTime) throws DukeException {
        super(description);
        this.dateTime = TimeStamp.of(dateTime);
    }

    /**
     * Returns a Deadline Task constructed with data read from File.
     *
     * @param data Data of a Deadline Task.
     * @return Deadline Task or null if data is corrupted.
     */
    public static Deadline readData(String[] data) {
        if (data.length < 4) {
            return null;
        }
        Deadline newDeadline = new Deadline(data[2], data[3]);
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
        return "D|" + symbol + "|" + this.description + "|" + this.dateTime;
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
