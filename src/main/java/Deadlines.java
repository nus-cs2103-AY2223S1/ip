public class Deadlines extends Task {
    String byDate;
    Deadlines(String name, String byDate) {
        super(name);
        this.byDate = byDate;
    }

    @Override
    public String writeData() {
        int mark = done ? 1 : 0;
        return "D#" + mark + "#" + this.name + "#" + this.byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + byDate + ")";
    }
}
