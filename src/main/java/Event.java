public class Event extends Task {
    protected String date;


    public Event (String description, String date){
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date + ")";
    }

    @Override
    public String saveFormat() {
        if (this.isDone) {
            return "E | 1 | " + this.description + " | " + this.date;
        } else {
            return "E | 0 | " + this.description + " | " + this.date;
        }
    }
} 
