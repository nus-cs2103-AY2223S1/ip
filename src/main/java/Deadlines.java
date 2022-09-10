public class Deadlines extends Task {

    protected String deadlineby;

    public Deadlines(String description) {
        super(description);
    }

    public Deadlines(String description,String deadlineby) {
        super(description);
        this.deadlineby = deadlineby;
    }

    public String getDeadLine() {
        return this.deadlineby;
    }

    public String getDeadLineTask() {
        return description;
    }

    public String getItem() {
        return "[D]";
    }


}
