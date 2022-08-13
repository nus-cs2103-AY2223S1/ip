public class Deadline extends Task{

    private String time;

    public Deadline(String title, String time){
        super(title, "deadline");
        this.time = time;
    }

    @Override
    public String toString() {
        return (super.toString() + " (by: " + this.time + ")" );
    }
}
