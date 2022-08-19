public class Deadlines extends Task {
    private String date;
    public Deadlines(String description, String date){
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + this.date + ")";
    }
}
