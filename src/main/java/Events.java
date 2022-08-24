public class Events extends Task {
    String atDate;
    Events(String name, String atDate) {
        super(name);
        this.atDate = atDate;
    }

    @Override
    public String writeData() {
        int mark = done ? 1 : 0;
        return "E#" + mark + "#" + this.name + "#" + this.atDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + atDate + ")";
    }
}
