public class Deadline extends Task{

    private String date;

    public Deadline(String item, String date) {
        super(item);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "by:" + date;
    }
}
