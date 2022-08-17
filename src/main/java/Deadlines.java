public class Deadlines extends Task {
    String byDate;
    Deadlines(String name, String byDate) {
        super(name);
        this.byDate = byDate;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + byDate + ")";
    }
}
