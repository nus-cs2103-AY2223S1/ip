public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Rewrites this deadline task into the save file format
     * @return String to be stored in save file
     */
    @Override
    public String saveData() {
        String marked = this.isDone ? "1" : "0";
        return "D | " + marked + " | " + this.description + " | " + this.by;
    }
}