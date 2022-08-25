public class Deadline extends TimedTask {
    public Deadline(String description, String rawDateTime) throws DukeException {
        super(description, rawDateTime);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getFormattedTime());
    }

    @Override
    public String getSaveFormat() {
        return String.format("D | %s | %s", super.getSaveFormat(), getFormattedTime());
    }
}
