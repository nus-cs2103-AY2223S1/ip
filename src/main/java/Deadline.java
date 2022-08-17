public class Deadline extends Todo{
    private String date;
    public Deadline(String title, String date) {
        super(title);
        this.date = date;
    }
    public String toString() {
        return String.format("%s (by: %s)", super.toString().replace("[T]", "[D]"), this.date);
    }
}
