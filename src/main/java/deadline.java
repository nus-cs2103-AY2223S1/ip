import java.util.ArrayList;

public class deadline extends Task {
    protected formatDate date;

    public deadline(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + date + ")";
    }
}
