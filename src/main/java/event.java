public class event extends Task {
    protected formatDate day;

    public event (String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + day + ")";
    }

    public void setDay(String day) {
        this.day = day;
    }
}
