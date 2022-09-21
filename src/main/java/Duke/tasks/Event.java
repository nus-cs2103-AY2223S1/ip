package Duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Event extends Task {

    protected final LocalDate at;


    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    public String format(LocalDate time) {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public ArrayList<String> toList() {
        ArrayList<String> product = new ArrayList<>();
        product.add("E");
        product.addAll(super.toList());
        product.add(at.toString());
        return product;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + format(at) + ")";

    }
}
