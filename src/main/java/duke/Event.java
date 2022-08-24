package duke;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }

    // duke.Event strings look like: [E][ ] project meeting (at: Aug 6th 2-4pm)
    public static Event stringToEvent(String s) throws DukeException {
        if (!s.startsWith("[E][")) {
            throw new DukeException("This string is not a duke.Event string!");
        }

        char isDoneString = s.charAt(4); //[E][X] checks if X is present
        char X = 'X';
        boolean isDone = isDoneString == X;

        int idxOfAt = s.indexOf("(at:");

        String description = s.substring(7, idxOfAt);
        String dateString = s.substring(idxOfAt + 5, s.length() - 1); // to avoid the brackets
        return new Event(description, dateString, isDone);
    }

    public static void main(String[] args) throws DukeException {
        String testString = "[E][ ] project meeting (at: Aug 6th 2-4pm)";
        System.out.println(stringToEvent(testString).toString().equals(testString));
    }
}
