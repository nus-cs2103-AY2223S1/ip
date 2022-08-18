public class Deadline extends Item{
    private final String itemType = "[D]";
    private final String dueDate;

    public Deadline(String item, String dueDate) {
        super(item);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return this.itemType + super.toString() + " (by: " + this.dueDate + ")";
    }
}
