public class Deadline extends Task{

    protected String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    public Deadline(String name, String date, boolean isDone) {
        super(name, isDone);
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public String tag() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", tag(), super.toString(), getDate());
    }

    @Override
    public String savedString() {
        return String.format("%s,%s,%s", tag(), super.savedString(), getDate());
    }
}
