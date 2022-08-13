public class Deadline extends ToDo {

    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s %s (by: %s)", super.getStatusIcon(), description, by);
    }
}
