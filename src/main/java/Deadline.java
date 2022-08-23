public class Deadline extends Task {
    private String by;
    public Deadline(String description, boolean done, String by)
    {
        super(description, done);
        this.by = by;
    }
    @Override
    public String toString()
    {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public char getType()
    {
        return 'D';
    }

    @Override
    public String getDetail()
    {
        return by;
    }
}