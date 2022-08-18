public class event extends Task {
    protected String day;

    public event (String desc) {
        super(desc);
        this.day = day;
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + day + ")";
    }
}
