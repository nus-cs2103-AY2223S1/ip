public class Deadline extends Task {
    private final char tag = 'D';
    public static final String DELIMITER = " /by ";
    private String time;

    public Deadline(String description) {
        super(description.split(Deadline.DELIMITER)[0].substring(9));
        this.time = description.split(Deadline.DELIMITER)[1];
    }

    @Override
    public String printTask() {
        return String.format("[%s]%s (by: %s)",
                this.tag,
                super.printTask(),
                this.time);
    }
}
