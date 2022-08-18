public class Events extends Task {

    private String time;

    public Events(String[] input) {
        super(input[0]);
        this.time = input[1];

    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",this.getDone() ? "X" : " ", this.getTask(), this.time);
    }
}
