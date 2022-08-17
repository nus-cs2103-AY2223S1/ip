public class Events extends Task {
    String atDate;
    Events(String name, String atDate) {
        super(name);
        this.atDate = atDate;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + atDate + ")";
    }
}
