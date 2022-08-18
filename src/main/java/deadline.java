import java.util.ArrayList;

public class deadline extends Task {
    protected String date;

    public deadline(String desc) {
        super(desc);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + date + ")";
    }
}
