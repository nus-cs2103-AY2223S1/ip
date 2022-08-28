package duke;

public class Event extends Task {

    private String at;

    public Event(String name, boolean isDone, String at) throws DukeTaskException {
        super(name, isDone);
        if (at.equals("")) {
            throw new DukeTaskException("time can't be empty");
        }
        this.at = at;
    }

    public static Event load(String s) throws DukeException {
        String at = s.substring(1, s.indexOf("|")).trim();
        String name = s.substring(s.indexOf("|") + 1, s.lastIndexOf("|")).trim();
        String isDone = s.substring(s.lastIndexOf("|") + 1).trim();
        return new Event(name, Boolean.parseBoolean(isDone), at);
    }

    @Override
    public String saveString() {
        return "E " + at + "|" + super.saveString();
    }

    @Override
    public String toString() {
        String temp;
        if (super.isDone) {
            temp = "[X] " + super.name;
        } else {
            temp = "[ ] " + name;
        }
        return "[E]" + temp + " (at: " + at + ")";
    }


}
