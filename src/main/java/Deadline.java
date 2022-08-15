public class Deadline extends Task {
    private String dateTime;

    public Deadline(String taskString, String dateTime) {
        super(taskString);
        this.dateTime = dateTime;
    }

    @Override
    public char getChar() {
        return 'D';
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.dateTime + ")";
    }
}
