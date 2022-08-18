public class Deadline extends Task {

    public Deadline(String desc) {
        super(desc);
        super.description = desc.replace("/by", "(by:");
        super.description = super.description + ")";
        super.isDone = false;
        super.type = "D";
    }
}
