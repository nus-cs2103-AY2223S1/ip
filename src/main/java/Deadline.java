public class Deadline extends Task {

    private String by;

    public Deadline(String name, boolean isDone, String by) throws DukeTaskException {
        super(name, isDone);
        if (by.equals("") || by.equals(" ")) {
            throw new DukeTaskException("time can't be empty");
        }
        this.by = by;
    }

    public static Deadline load(String s) throws DukeException {
        String by = s.substring(1, s.indexOf("|")).trim();
        String name = s.substring(s.indexOf("|") + 1, s.lastIndexOf("|")).trim();
        String isDone = s.substring(s.lastIndexOf("|") + 1).trim();
        return new Deadline(name, Boolean.parseBoolean(isDone), by);
    }

    @Override
    public String saveString() {
        return "D " + by + "|" + super.saveString();
    }

    @Override
    public String toString() {
        String temp;
        if (super.isDone) {
            temp = "[X] " + super.name;
        } else {
            temp = "[ ] " + name;
        }
        return "[D]" + temp + " (by: " + by + ")";
    }


}
