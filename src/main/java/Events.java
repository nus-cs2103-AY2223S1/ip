public class Events extends Task {

    private String time;

    public Events(String task, String time) {
        super(task);
        this.time = time;

    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",this.getDone() ? "X" : " ", this.getTask(), this.time);
    }
}
