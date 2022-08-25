public class Events extends Task{
    private String date;

    public Events(String description, String date){
        super(description);
        this.date = date;
    }

    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + "(" + this.date + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + this.date + ")";
    }
}
