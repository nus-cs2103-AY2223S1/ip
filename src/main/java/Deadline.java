import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Deadline extends Task {

    protected final LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }


    public String format(LocalDate time) {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public List<String> toList() {
        List<String> product = new ArrayList<>();
        product.add("D");
        product.addAll(super.toList());
        product.add(by.toString());
        return product;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + format(by) + ")";
    }
}
