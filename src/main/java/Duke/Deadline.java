package Duke;

public class Deadline extends Task {

    String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }
    @Override
    public String storeToString() {
        return "D|" + this.binarytoString() + "|" + this.description.substring(0,description.length()-1) + "|" + this.time;
    }

    @Override
    public String toString() {
        return "[D]"  + super.toString() + "(by: " + this.time + ")";
    }

}