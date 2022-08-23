public class Deadline extends Task{

    private String time;

    public Deadline(String title, String time, boolean done){
        super(title, "deadline", done);
        this.time = time;
    }

    @Override
    public String toString() {
        return (super.toString() + " (by: " + this.time + ")" );
    }

    public String getTime() {
        return time;
    }
}
