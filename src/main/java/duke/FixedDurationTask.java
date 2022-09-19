package duke;

/**
 * A FixedDurationTask is a type of Task with a fixed duration.
 */
public class FixedDurationTask extends Task {

    private String duration;

    /**
     * Constructs an Event.
     *
     * @param name Name of the event.
     * @param isDone Whether the event has been completed.
     * @param duration Time when the event is held.
     * @throws DukeTaskException  If time is empty.
     */
    public FixedDurationTask(String name, boolean isDone, String duration) throws DukeTaskException {
        super(name, isDone);
        if (duration.equals("")) {
            throw new DukeTaskException("time can't be empty");
        }
        this.duration = duration;
    }

    /**
     * Loads a FixedDurationTask from a string from the save file.
     *
     * @param s String representing contents of a FixedDurationTask.
     * @return FixedDurationTask with specified parameters.
     * @throws DukeException  If time specifier in string is empty.
     */
    public static FixedDurationTask load(String s) throws DukeException {
        String duration = s.substring(1, s.indexOf("|")).trim();
        String name = s.substring(s.indexOf("|") + 1, s.lastIndexOf("|")).trim();
        String isDone = s.substring(s.lastIndexOf("|") + 1).trim();
        return new FixedDurationTask(name, Boolean.parseBoolean(isDone), duration);
    }

    /**
     * Returns a string to save to the save file.
     *
     * @return String representing the FixedDurationTask contents.
     */
    @Override
    public String saveString() {
        return "F " + duration + "|" + super.saveString();
    }

    /**
     * Returns the string representation of the FixedDurationTask.
     *
     * @return FixedDurationTask as a string.
     */
    @Override
    public String toString() {
        String temp;
        if (super.isDone) {
            temp = "[X] " + super.name;
        } else {
            temp = "[ ] " + name;
        }
        return "[F]" + temp + " (needs: " + duration + ")";
    }


}
