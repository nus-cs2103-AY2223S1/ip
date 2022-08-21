public class Deadline extends Task{
    protected String date;
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + this.date + ")");
    }

    @Override
    public String toStorageString() {
        String doneDescriptionString = super.toStorageString();
        return "D" + Task.STORAGE_DELIMITER
                + doneDescriptionString + Task.STORAGE_DELIMITER
                + this.date;
    }
}