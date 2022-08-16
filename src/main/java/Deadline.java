public class Deadline extends Task{

    protected String date;

    public Deadline(String name, String date) {
        super(name);
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

}
